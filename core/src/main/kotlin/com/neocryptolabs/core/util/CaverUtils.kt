package com.neocryptolabs.core.util

import com.neocryptolabs.core.model.BaseBalance
import com.klaytn.caver.Caver
import com.klaytn.caver.contract.Contract
import com.klaytn.caver.contract.SendOptions
import com.klaytn.caver.transaction.TxPropertyBuilder
import org.springframework.util.StringUtils
import java.io.InterruptedIOException
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

const val KLAY_THRESHOLD = 0.3
const val EXCHANGE_RATE = 1160
val TOKEN_CONTRACT_TARGETS = TokenInfo.values()
        //listOf(TokenInfo.KLAY, TokenInfo.KSP, TokenInfo.KUSDT, TokenInfo.KUSDC, TokenInfo.KDAI, TokenInfo.KAI)

fun connectCaver(nodeUrl: String = "http://localhost:8551", keystore: String, pwd: String): Pair<Caver, String> {
    val caver = Caver(nodeUrl)
    val keyring = caver.wallet.keyring.decrypt(keystore, pwd)
    caver.wallet.add(keyring)
    return Pair(caver, keyring.address)
}

fun buildTokenContract(caver: Caver): HashMap<String, Contract> {
    val tokenContracts = hashMapOf<String, Contract>()
    for (token in TOKEN_CONTRACT_TARGETS) {
        val address = token.address
        val abi = if (token.name == "KSP") Constants.kspAbi else Constants.kipAbi
        val contract = caver.contract.create(abi, address)
        tokenContracts[token.name] = contract
    }
    return tokenContracts
}

fun getKlayBalance(caver: Caver, address: String): Double {
    val token = "KLAY"
    val klay = caver.rpc.klay.getBalance(address).send()
    val klayAmount = convertRevDegree(token, klay.value.toString())
    return klayAmount.toDouble()
}

fun getBalance(caver: Caver, address: String, tokenContracts: HashMap<String, Contract>): BaseBalance {
    var token = "KLAY"
    try {
        // KLAY
        val klay = caver.rpc.klay.getBalance(address).send()
        val klayAmount = convertRevDegree(token, klay.value.toString()).toDouble()

        // KUSDT / KDAI
        token = "KUSDT"
        val kusdtContract = tokenContracts[token]!!
        val kusdt = kusdtContract.call("balanceOf", address)[0]
        val kusdtAmount = convertRevDegree(token, kusdt.value.toString()).toDouble()

        token = "KDAI"
        val kdaiContract = tokenContracts[token]!!
        val kdai = kdaiContract.call("balanceOf", address)[0]
        val kdaiAmount = convertRevDegree(token, kdai.value.toString()).toDouble()

        return BaseBalance(klayAmount, kusdtAmount, kdaiAmount)
    } catch (e: Exception) {
        println("Exception during getBalance : addr=${address}, token=${token}")
        throw e
    }
}

fun getJbot2Balance(address: String, tokenContracts: HashMap<String, Contract>): HashMap<String, Double> {
    val res = hashMapOf<String, Double>()
    var token = "KWBTC"

    val kusdtContract = tokenContracts[token]!!
    val kusdt = kusdtContract.call("balanceOf", address)[0]
    val kusdtAmount = convertRevDegree(token, kusdt.value.toString())
    res[token] = kusdtAmount.toDouble()

    token = "KETH"
    val kdaiContract = tokenContracts[token]!!
    val kdai = kdaiContract.call("balanceOf", address)[0]
    val kdaiAmount = convertRevDegree(token, kdai.value.toString())
    res[token] = kdaiAmount.toDouble()
    return res
}

fun getBalanceKokoa(caver: Caver, address: String, tokenContracts: HashMap<String, Contract>): HashMap<String, Double> {
    val res = hashMapOf<String, Double>()
    var token = "KLAY"
    val klay = caver.rpc.klay.getBalance(address).send()
    val klayAmount = convertRevDegree(token, klay.value.toString())
    res[token] = klayAmount.toDouble()

    token = "KUSDT"
    val kusdtContract = tokenContracts[token]!!
    val kusdt = kusdtContract.call("balanceOf", address)[0]
    val kusdtAmount = convertRevDegree(token, kusdt.value.toString())
    res[token] = kusdtAmount.toDouble()

    token = "KDAI"
    val kdaiContract = tokenContracts[token]!!
    val kdai = kdaiContract.call("balanceOf", address)[0]
    val kdaiAmount = convertRevDegree(token, kdai.value.toString())
    res[token] = kdaiAmount.toDouble()

    token = "KOKOA"
    val kokoaContract = tokenContracts[token]!!
    val kokoa = kokoaContract.call("balanceOf", address)[0]
    val kokoaAmount = convertRevDegree(token, kokoa.value.toString())
    res[token] = kokoaAmount.toDouble()

    token = "KSD"
    val ksdContract = tokenContracts[token]!!
    val ksd = ksdContract.call("balanceOf", address)[0]
    val ksdAmount = convertRevDegree(token, ksd.value.toString())
    res[token] = ksdAmount.toDouble()

    return res
}

fun exchangeKctPos(kspContract: Contract, address: String,
                   fromToken: String, toToken: String,
                   fromAmount: Double, toAmount: Double,
                   path: List<String>): Boolean {

    val contract = kspContract
    val fromTokenAddress = TokenInfo.valueOf(fromToken).address
    val toTokenAddress = TokenInfo.valueOf(toToken).address
    val from = BigDecimal(convertDegree(fromToken)).multiply(BigDecimal.valueOf(fromAmount))
    val to = BigDecimal(convertDegree(toToken)).multiply(BigDecimal.valueOf(toAmount))
    val addrPath = arrayListOf<String>()
    for (p in path) {
        addrPath.add(TokenInfo.valueOf(p).address)
    }
    try {
        val sendOptions = SendOptions(address, BigInteger.valueOf(5000000))
        sendOptions.feeDelegation = false
        val exchangeKctPos = contract.send(sendOptions,
                "exchangeKctPos", fromTokenAddress, from, toTokenAddress, to, addrPath)
        if (!StringUtils.isEmpty(exchangeKctPos.txError)) {
            println("exchangeKctPos failed: ${exchangeKctPos.txError}, ${exchangeKctPos.logs}")
            return false
        }
        return true
    } catch (e: Exception) {
        println("exchangeKctPos failed: ${e.message}")
        return false
    }
}

fun transferKlay(caver: Caver, sender: String, receiver: String, amount: String): Boolean {
    val toKlay = BigDecimal(convertDegree("KLAY")).multiply(BigDecimal(amount))
    val value = toKlay.toBigInteger()
    println("Transfer klay ${value}")
    val valueTransfer = caver.transaction.valueTransfer.create(
            TxPropertyBuilder.valueTransfer()
                    .setFrom(sender)
                    .setTo(receiver)
                    .setValue(value)
                    .setGas(BigInteger.valueOf(25000))
    ).sign(caver.wallet.getKeyring(sender))
    val receipt = caver.rpc.klay.sendRawTransaction(valueTransfer.rawTransaction).send()
    return if (receipt.hasError()) {
        println("[${receipt.error.code}] ${receipt.error.message}: ${receipt.result}")
        false
    } else {
        true
    }
}

fun transferKdai(contract: Contract, sender: String, receiver: String, amount: Double): Boolean =
        transferKdai(contract, sender, receiver, amount.toString())
fun transferKdai(contract: Contract, sender: String, receiver: String, amount: String): Boolean {
    val tokenAmount = BigDecimal(convertDegree("KDAI")).multiply(BigDecimal(amount))
    println("Transfer kdai ${tokenAmount}")
    val sendOptions = SendOptions(sender, BigInteger.valueOf(60000))
    val transfer = contract.send(sendOptions,
            "transferFrom", sender, receiver, tokenAmount.toBigInteger())
    return if (!StringUtils.isEmpty(transfer.txError)) {
        println("Transfer KDAI failed: ${transfer.txError}")
        false
    } else {
        true
    }
}


fun transferKusdt(contract: Contract, sender: String, receiver: String, amount: Double): Boolean =
        transferKusdt(contract, sender, receiver, amount.toString())
fun transferKusdt(contract: Contract, sender: String, receiver: String, amount: String): Boolean =
        transferCommon("KUSDT", contract, sender, receiver, amount)

fun transferKusdc(contract: Contract, sender: String, receiver: String, amount: Double): Boolean =
        transferKusdc(contract, sender, receiver, amount.toString())
fun transferKusdc(contract: Contract, sender: String, receiver: String, amount: String): Boolean =
        transferCommon("KUSDC", contract, sender, receiver, amount)

fun transferKai(contract: Contract, sender: String, receiver: String, amount: Double): Boolean =
        transferKai(contract, sender, receiver, amount.toString())
fun transferKai(contract: Contract, sender: String, receiver: String, amount: String): Boolean =
        transferCommon("KAI", contract, sender, receiver, amount)

fun transferKsd(contract: Contract, sender: String, receiver: String, amount: Double): Boolean =
        transferKsd(contract, sender, receiver, amount.toString())
fun transferKsd(contract: Contract, sender: String, receiver: String, amount: String): Boolean =
        transferCommon("KSD", contract, sender, receiver, amount)

fun transferClam(contract: Contract, sender: String, receiver: String, amount: String): Boolean =
        transferCommon("CLAM", contract, sender, receiver, amount)

private fun transferCommon(token: String, contract: Contract, sender: String, receiver: String, amount: String): Boolean {
    val tokenAmount = BigDecimal(convertDegree(token)).multiply(BigDecimal(amount))
    println("Transfer $token ${tokenAmount}")
    val sendOptions = SendOptions(sender, BigInteger.valueOf(60000))
    return try {
        val transfer = contract.send(sendOptions,
                "transfer", receiver, tokenAmount.toBigInteger())
        if (!StringUtils.isEmpty(transfer.txError)) {
            println("Transfer $token failed: ${transfer.txError}")
            false
        } else {
            true
        }
    } catch (e: Exception) {
        false
    }
}

fun getSwapRatio(result: HashMap<String, BigDecimal>, swapContract: Contract, swap: String, amount: Double = 1.0) {
    try {
        val tokenParts = swap.split("_")
        val srcToken = tokenParts[0]
        val srcTokenInfo = TokenInfo.valueOf(srcToken)
        val srcTokenAddr = srcTokenInfo.address
        val desToken = tokenParts[1]

        val inDegree = convertDegree(srcToken)
        val quant = BigDecimal(inDegree).multiply(BigDecimal(amount))
        val pos = swapContract.call("estimatePos", srcTokenAddr, quant)
        val outDegree = convertDegree(desToken)


        val ratio = BigDecimal(pos[0].value.toString()).divide(BigDecimal(outDegree))
        result[swap] = ratio.divide(BigDecimal(amount), 10, RoundingMode.DOWN)
    } catch (e: InterruptedIOException) {
        println("Interrupted getRatio")
    } catch (e: Exception) {
        println("Get Ratio failed: $e")
        result[swap] = BigDecimal.ZERO
    }
}