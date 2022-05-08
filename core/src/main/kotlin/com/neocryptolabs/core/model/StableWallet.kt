package com.neocryptolabs.core.model

import com.neocryptolabs.core.util.*
import com.klaytn.caver.Caver
import com.klaytn.caver.contract.Contract
import kotlin.math.round
import kotlin.math.roundToInt

data class StableWallet(
        val inst: String,
        val keystore: String,
        val pwd: String,
        var klay: Double = 0.0,
        var kusdt: Double = 0.0,
        var kdai: Double = 0.0,
        var kai: Double = 0.0,
        var kusdc: Double = 0.0,
) {
    lateinit var caver: Caver
    lateinit var address: String
    lateinit var tokenContracts: HashMap<String, Contract>

    fun isCaverInitialized() =
            this::caver.isInitialized

    fun sum() =
            roundThreeDigit(kusdt + kdai)

    fun krw() =
            (sum() * EXCHANGE_RATE).roundToInt()

    fun refresh(bal: BaseBalance) {
        klay = bal.klay
        kusdt = bal.kusdt
        kdai = bal.kdai
        round()
    }

    fun round() {
        klay = roundThreeDigit(klay)
        kusdt = roundThreeDigit(kusdt)
        kdai = roundThreeDigit(kdai)
    }

    override fun toString(): String {
        return "StableWallet(inst='$inst', sum=${sum()}, klay=$klay, kusdt=$kusdt, kdai=$kdai, address='$address')"
    }
}

data class TodayEstimatedWallet(
        val name: String,
        var start: Double = 0.0,
        var current: Double = 0.0,
        var base: Double = 0.0,
        var kdai: Double = 0.0,     // CUR
        var kusdt: Double = 0.0,    // CUR
        var kai: Double = 0.0,      // CUR
        var kusdc: Double = 0.0,    // CUR
        var exchangeRate: Int = EXCHANGE_RATE
) {
    fun earn() =
            roundThreeDigit(current - start)

    fun earnFromBase() =
            roundThreeDigit(current - base)

    fun earnKrw() =
            numWithCommasKrw((earn() * exchangeRate).roundToInt())

    fun curKrw() =
            numWithCommasKrw((current * exchangeRate).roundToInt())

    fun earnKrwFromBase() =
            numWithCommasKrw(earnFromBase() * exchangeRate)

    fun earnPctFromBase() =
            if (base != 0.0) {
                percentTwoDigit((current - base) / base)
            } else {
                percentTwoDigit(0.0)
            }

    fun round() {
        start = roundTwoDigit(start)
        current = roundTwoDigit(current)
        base = roundTwoDigit(base)
        kdai = roundTwoDigit(kdai)
        kusdt = roundTwoDigit(kusdt)
        kai = roundTwoDigit(kai)
        kusdc = roundTwoDigit(kusdc)
    }

    fun kusdtPct() =
            if (current != 0.0) percentTwoDigitWo(kusdt / current) else 0

    fun kdaiPct() =
            if (current != 0.0) percentTwoDigitWo(kdai / current) else 0

    fun kaiPct() =
            if (current != 0.0) percentTwoDigitWo(kai / current) else 0

    fun kusdcPct() =
            if (current != 0.0) percentTwoDigitWo(kusdc / current) else 0
}