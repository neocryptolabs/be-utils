package com.neocryptolabs.core.model

import com.neocryptolabs.core.util.roundThreeDigit

data class BaseBalance(
    var klay: Double,
    var kusdt: Double,
    var kdai: Double,
) {
    fun round() {
        klay = roundThreeDigit(klay)
        kusdt = roundThreeDigit(kusdt)
        kdai = roundThreeDigit(kdai)
    }
}