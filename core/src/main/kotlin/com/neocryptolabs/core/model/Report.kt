package com.neocryptolabs.core.model

import com.neocryptolabs.core.util.numWithCommasKrw
import com.neocryptolabs.core.util.percentTwoDigit
import com.neocryptolabs.core.util.roundFourDigit
import kotlin.math.roundToInt

data class Report(
        var total: Int = 0,
        var success: Int = 0,
        var fail: Int = 0,
        var gain: Double = 0.0,
) {
    fun reset() {
        total = 0
        success = 0
        fail = 0
        gain = 0.0
    }

    fun getPct() =
            if (total == 0) "0%" else percentTwoDigit(success / total.toDouble())

    fun getEarn() =
            numWithCommasKrw(gain.roundToInt())

    fun getAvgEarnKrw() =
            if (success == 0) 0 else numWithCommasKrw((gain / success).roundToInt())

    fun doSuccess(money: Double) {
        total++
        success++
        gain += money
    }

    fun doFail() {
        total++
        fail++
    }

    fun getReport() =
            "[${this.success}/${this.total}] ==> *${String.format("%,d", gain.roundToInt())}원*)"
}
