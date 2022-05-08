package com.neocryptolabs.core.model

import com.neocryptolabs.core.util.EXCHANGE_RATE
import com.neocryptolabs.core.util.cvrtRatio
import com.neocryptolabs.core.util.numWithCommasKrw
import com.neocryptolabs.core.util.roundTwoDigit
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import kotlin.math.roundToInt

@JsonIgnoreProperties(ignoreUnknown = true)
data class ExchangerPrice(
        var daiPrc: Double = 0.0,
        var klayPrc: Double = 0.0,
        var orcPrc: Double = 0.0,
        var xrpPrc: Double = 0.0,
        var ethPrc: Double = 0.0,
        var btcPrc: Double = 0.0,
        var sandboxPrc: Double = 0.0,
        var boraPrc: Double = 0.0,
        var plaPrc: Double = 0.0,
) {
    val priceMap = hashMapOf<String, Int>()
    var priceRawMap = hashMapOf<String, Double>()

    fun update(aNew: ExchangerPrice) {
        daiPrc = aNew.daiPrc
        klayPrc = aNew.klayPrc
        orcPrc = aNew.orcPrc
        xrpPrc = aNew.xrpPrc
        ethPrc = aNew.ethPrc
        btcPrc = aNew.btcPrc
        sandboxPrc = aNew.sandboxPrc
        boraPrc = aNew.boraPrc
        plaPrc = aNew.plaPrc
        priceMap["KLAY"] = klayPrc.roundToInt()
        priceMap["KXRP"] = xrpPrc.roundToInt()
        priceMap["KETH"] = ethPrc.roundToInt()
        priceMap["KWBTC"] = btcPrc.roundToInt()
        priceMap["KDAI"] = daiPrc.roundToInt()
        priceMap["KUSDT"] = daiPrc.roundToInt()
    }

    fun update(map: HashMap<String, Double>) {
        daiPrc = map["DAI"] ?: 0.0
        klayPrc = map["KLAY"] ?: 0.0
        orcPrc = map["ORC"] ?: 0.0
        xrpPrc = map["XRP"] ?: 0.0
        ethPrc = map["ETH"] ?: 0.0
        btcPrc = map["BTC"] ?: 0.0
        sandboxPrc = map["SAND"] ?: 0.0
        boraPrc = map["BORA"] ?: 0.0
        plaPrc = map["PLA"] ?: 0.0
        priceMap["KLAY"] = klayPrc.roundToInt()
        priceMap["KXRP"] = xrpPrc.roundToInt()
        priceMap["KETH"] = ethPrc.roundToInt()
        priceMap["KWBTC"] = btcPrc.roundToInt()
        priceMap["KDAI"] = daiPrc.roundToInt()
        priceMap["KUSDT"] = daiPrc.roundToInt()

        priceRawMap.putAll(map)
    }
}

data class Price(
        val name: String,
        val exchange: Double,
        val ksp: Double,
) {

    fun roundKsp() =
            roundTwoDigit(ksp)

    fun getRatio() =
            cvrtRatio(exchange, ksp)
}

data class ChartData(
        var klayPrc: Double,
        var klayKusdt: Double,
        var tokenPrc: Double,
        var tokenKusdt: Double,
) {
    fun round(): ChartData {
        return ChartData(
                klayPrc = roundTwoDigit(klayPrc),
                klayKusdt = roundTwoDigit(klayKusdt),
                tokenPrc = roundTwoDigit(tokenPrc),
                tokenKusdt = roundTwoDigit(tokenKusdt),
        )
    }
}

data class EstimatedAsset(
        var stableAmount: Double,
        var tokenAmount: Double,
        var tokenPrice: Double,
) {
    fun getStable() =
            (stableAmount * EXCHANGE_RATE).roundToInt()

    fun getStableKrw() =
            numWithCommasKrw(getStable())

    fun getToken() =
            (tokenAmount * tokenPrice).roundToInt()

    fun getTokenKrw() =
            numWithCommasKrw(getToken())

    fun getTotalKrw() =
            numWithCommasKrw(getStable() + getToken())
}

data class EstimatedBuy (
        val thousand: Double,
        var amount1000: Double,
        var amount2000: Double,
        var amount5000: Double,
        var amount10000: Double,
        var amount15000: Double,
        var targetPrc: Int,
) {

    fun round(): EstimatedBuy {
        return EstimatedBuy(
                thousand = thousand,
                amount1000 = roundTwoDigit(amount1000),
                amount2000 = roundTwoDigit(amount2000),
                amount5000 = roundTwoDigit(amount5000),
                amount10000 = roundTwoDigit(amount10000),
                amount15000 = roundTwoDigit(amount15000),
                targetPrc = targetPrc
        )
    }

    fun getAmount1000Krw() =
            numWithCommasKrw(amount1000.roundToInt() * targetPrc)

    fun getAmount2000Krw() =
            numWithCommasKrw(amount2000.roundToInt() * targetPrc)

    fun getAmount5000Krw() =
            numWithCommasKrw(amount5000.roundToInt() * targetPrc)

    fun getAmount10000Krw() =
            numWithCommasKrw(amount10000.roundToInt() * targetPrc)

    fun getAmount15000Krw() =
            numWithCommasKrw(amount15000.roundToInt() * targetPrc)

    fun get1000() =
            roundTwoDigit(thousand)

    fun get2000() =
            roundTwoDigit(thousand * 2)

    fun get5000() =
            roundTwoDigit(thousand * 5)

    fun get10000() =
            roundTwoDigit(thousand * 10)

    fun get15000() =
            roundTwoDigit(thousand * 15)

}

data class EstimatedSell (
        val totalAmount: Double,
        var pct10: Double,
        var pct20: Double,
        var pct50: Double,
        var pct100: Double,
        var targetPrc: Int,
) {
    fun round() =
            EstimatedSell(
                    totalAmount,
                    roundTwoDigit(pct10),
                    roundTwoDigit(pct20),
                    roundTwoDigit(pct50),
                    roundTwoDigit(pct100),
                    targetPrc
            )

    fun getPct10Krw() =
            numWithCommasKrw(pct10 * targetPrc)

    fun getPct20Krw() =
            numWithCommasKrw(pct20 * targetPrc)

    fun getPct50Krw() =
            numWithCommasKrw(pct50 * targetPrc)

    fun getPct100Krw() =
            numWithCommasKrw(pct100 * targetPrc)

    fun get10() =
            roundTwoDigit(totalAmount * 0.1)

    fun get20() =
            roundTwoDigit(totalAmount * 0.2)

    fun get50() =
            roundTwoDigit(totalAmount * 0.5)

    fun get100() =
            roundTwoDigit(totalAmount)

}