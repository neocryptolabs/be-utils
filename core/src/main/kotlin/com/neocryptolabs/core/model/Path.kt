package com.neocryptolabs.core.model

import com.neocryptolabs.core.util.*
import java.math.BigDecimal

data class SwapPath(
    val from: String,
    val to: String,
    val path: Path,
)

data class Path(
    val amount: Double,
    val path: ArrayList<String>
)

data class KspPath(
    val path: ArrayList<String>,
    val swapPathKsp: ArrayList<SwapInfoKsp>,
    val from: String,
    val to: String,
) {
    var fromToken = TokenInfo.valueOf(from)
    var toToken = TokenInfo.valueOf(to)
    var result: Double = 0.0
    var estimation: Double = 0.0

    fun applyRatio(ratio: HashMap<SwapInfoKsp, BigDecimal>): Double {
        var amount = 1.0
        for (sp in swapPathKsp) {
            val mult = (ratio[sp] ?: BigDecimal.ZERO).toDouble()
            amount *= mult
        }
        result = amount
        return amount
    }
}

data class GeneralPath(
    val path: ArrayList<TokenInfo>,
    val swapPath: ArrayList<SwapSpec>,
    val from: TokenInfo,
    val to: TokenInfo,
) {
    var result: Double = 0.0
    fun applyRatio(ratio: HashMap<SwapSpec, BigDecimal>): Double {
        var amount = 1.0
        for (sp in swapPath) {
            val mult = (ratio[sp] ?: BigDecimal.ZERO).toDouble()
            amount *= mult
        }
        result = amount
        return amount
    }
}

fun toComplexPath(path: List<String>, from: TokenInfo, to: TokenInfo): ComplexPath {
    val fullPath = ArrayList(path).apply {
        add(0, from.name)
        add(to.name)
    }
    val newPath = ArrayList<SwapSpec>()
    for (i in fullPath.indices) {
        if (i == fullPath.size - 1) {
            break
        }
        val fromToken = TokenInfo.valueOf(fullPath[i])
        val toToken = TokenInfo.valueOf(fullPath[i+1])
        newPath.add(SwapSpec(fromToken, toToken, DEX.KLAYSWAP, ""))
    }

    return ComplexPath(
            newPath,
            from, to
    )
}

data class ComplexPath(
    val path: ArrayList<SwapSpec>,
    val from: TokenInfo,
    val to: TokenInfo,
) {
    var result: Double = 0.0
    fun applyRatio(ratio: HashMap<SwapSpec, BigDecimal>): Double {
        var amount = 1.0
        for (sp in path) {
            val mult = (ratio[sp] ?: BigDecimal.ZERO).toDouble()
            amount *= mult
        }
        result = amount
        return amount
    }

    fun buildParamPath() =
        path.map { listOf(it.from.address, it.to.address, dexToAddress[it.dex]!!) }

    fun buildTokenPath() =
        ArrayList<String>().apply {
            path.forEach { add(it.from.name) }
            add(path.last().to.name)
        }

    fun clone() =
        ComplexPath(ArrayList(path), from, to)

    fun buildCompressedPath(): ArrayList<SwapInfo> {
        val compressedPath =
            arrayListOf<SwapInfo>()

        var interPath = arrayListOf<String>()
        var curDex = path.first().dex

        for (i in path.indices) {
            if (i == 0) {
                interPath = arrayListOf()
                curDex = path[i].dex
                interPath.add(path[i].from.address)
                interPath.add(path[i].to.address)
                continue
            }

            if (curDex != path[i].dex) {
                compressedPath.add(SwapInfo(interPath[0], interPath[interPath.size-1], dexToAddress[curDex]!!, ArrayList(interPath)))

                interPath.clear()
                curDex = path[i].dex
                interPath.add(path[i].from.address)
                interPath.add(path[i].to.address)
            } else {
                interPath.add(path[i].to.address)
            }
        }

        // last
        if (from == TokenInfo.KLAY && to == TokenInfo.KLAY) {   // KLAYSWAP 으로만 이루어졌을 ComplexKlaySwap 만 해당
            val pop = interPath.removeLast()  // 마지막 KLAY 제거
            val last = interPath[interPath.size-1]
            compressedPath.add(SwapInfo(interPath[0], last, dexToAddress[curDex]!!, ArrayList(interPath)))
            compressedPath.add(SwapInfo(last, pop, dexToAddress[curDex]!!, arrayListOf(last, pop)))
        } else {
            compressedPath.add(SwapInfo(interPath[0], interPath[interPath.size-1], dexToAddress[curDex]!!, ArrayList(interPath)))
        }
        return compressedPath
    }
}

// [address from, address to, address dex, address[] tokens, address[] exchanges]
data class SwapInfo(
    var from: String,
    var to: String,
    val dex: String,
    val tokens: ArrayList<String>,
) {
    fun toList(): List<Any> {
        val exchanges = buildExchangeList()
        convertKlay()
        return listOf(from, to, dex, tokens, exchanges)
    }

    private fun buildExchangeList() =
        if (dex == dexToAddress[DEX.KLAYSWAP]) {
            val exchangeList = arrayListOf<String>()
            val fromToken = TokenInfo.values().first { it.address == from }.name
            val toToken = TokenInfo.values().first { it.address == to }.name

            if (tokens.isEmpty()) {
                exchangeList.add(SwapInfoKsp.valueOf("${fromToken}_${toToken}").address)
            }

            for (i in tokens.indices) {
                if (i == tokens.size - 1) {
                    break
                }
                val cur = TokenInfo.values().first { it.address == tokens[i] }.name
                val next = TokenInfo.values().first { it.address == tokens[i+1] }.name
                try {
                    exchangeList.add(getSwapInfoKspAddres(cur, next))
                } catch (e: Exception) {
                    println("${this} -> $e")
                    throw e
                }
            }
            exchangeList.toList()
        } else {
            listOf<String>()
        }

    private fun convertKlay() {
        var klay: String
        when (addressToDex[dex]) {
            DEX.DEFINIX -> {
                klay = TokenInfo.WKLAY_DEFINIX.address
            }
            DEX.CLAIMSWAP -> {
                klay = TokenInfo.WKLAY_CLAIM.address
            }
            DEX.PALA -> {
                klay = TokenInfo.WKLAY_PALA.address
            }
            else -> {
                klay = TokenInfo.KLAY.address
            }
        }

        if (from == TokenInfo.KLAY.address) {
            from = klay
        }

        if (to == TokenInfo.KLAY.address) {
            to = klay
        }

        for (i in tokens.indices) {
            if (tokens[i] == TokenInfo.KLAY.address) {
                tokens[i] = klay
            }
        }

    }

    override fun toString(): String {
        return "SwapInfo(from='${getTokenInfoName(from)}', to='${getTokenInfoName(to)}', dex='${addressToDex[dex]}', tokens=${tokens.map { getTokenInfoName(it) }})"
    }
}


