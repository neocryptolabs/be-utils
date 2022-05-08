package com.neocryptolabs.core.util

import java.math.BigDecimal
import java.math.BigInteger

fun convertDegree(token: TokenInfo) =
        BigInteger("10").pow(token.decimal).toString()

fun convertDegree(token: String) =
        convertDegree(TokenInfo.valueOf(token))

fun convertRevDegree(token: TokenInfo, amount: String) =
        BigDecimal(amount).divide(BigDecimal("10").pow(token.decimal)).toString()

fun convertRevDegree(token: String, amount: String) =
        convertRevDegree(TokenInfo.valueOf(token), amount)

fun convertAmountHumanToNetwork(token: TokenInfo, amount: Double) =
        BigDecimal(convertDegree(token)).multiply(BigDecimal.valueOf(amount))

fun convertAmountHumanToNetwork(token: String, amount: Double) =
        BigDecimal(convertDegree(TokenInfo.valueOf(token))).multiply(BigDecimal.valueOf(amount))

