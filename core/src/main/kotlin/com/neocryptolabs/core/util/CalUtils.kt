package com.neocryptolabs.core.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import kotlin.math.roundToInt

/**
 * 정수
 */
fun floorDegreeToMult5(value: Int) =
        value - (value % 5)

fun floorDegreeToMult0(value: Int) =
        value - (value % 10)

fun floorThreeDegree(value: Int) =
        value / 1000 * 1000

/**
 * 소수점
 */
fun roundTwoDigit(value: Double): Double =
        BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toDouble()

fun roundThreeDigit(value: Double): Double =
        BigDecimal(value).setScale(3, RoundingMode.HALF_EVEN).toDouble()

fun roundFourDigit(value: Double): Double =
        BigDecimal(value).setScale(4, RoundingMode.HALF_EVEN).toDouble()

fun floorDigit(value: Double) =
        BigDecimal(value).setScale(0, RoundingMode.FLOOR).toDouble()

fun floorFourDigit(value: Double) =
        BigDecimal(value).setScale(4, RoundingMode.FLOOR).toDouble()

fun floorEightDigit(value: Double) =
        BigDecimal(value).setScale(8, RoundingMode.FLOOR).toDouble()

fun percentTwoDigit(value: Double) =
        "${roundTwoDigit(value * 100)}%"

fun percentTwoDigitWo(value: Double) =
        "${roundTwoDigit(value * 100)}"

fun exchangeKrw(num: Int) =
        num * EXCHANGE_RATE

fun exchangeKrw(num: Double) =
        (num * EXCHANGE_RATE).roundToInt()

fun exchangeKrw(num: Double, exchangeRate: Int) =
        (num * exchangeRate).roundToInt()

fun numWithCommas(value: Int) =
        NumberFormat.getInstance().format(value)

fun numWithCommasKrw(value: Int) =
        "${NumberFormat.getInstance().format(value)}원"

fun numWithCommasKrw(value: Double) =
        "${NumberFormat.getInstance().format(value)}원"

fun cvrtKsp(compPrice: Double, price: Double) =
        roundThreeDigit(compPrice * price)

fun cvrtRatio(compPrice: Double, price: Double) =
        if (compPrice == 0.0 || price == 0.0) 0.0 else roundThreeDigit((compPrice - price) / price * 100)
