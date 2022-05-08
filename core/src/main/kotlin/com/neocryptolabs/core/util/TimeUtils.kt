package com.neocryptolabs.core.util

import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun curTs(): Timestamp =
        Timestamp.from(Instant.now())

/*
    Time conversion
 */
enum class ConvertNtimes(
    val desc: String, val n: Int
) {
    // Sec = 1, Milli = 10^-3, Micro = 10^-6, Nano = 10^-9
    // Upper
    NANO_TO_SEC("nano to seconds", 3),
    NANO_TO_MSEC("nano to milli", 2),
    NANO_TO_USEC("nano to micro", 1),
    USEC_TO_SEC("micro to seconds", 2),
    USEC_TO_MSEC("micro to milli", 1),
    MSEC_TO_SEC("milli to seconds", 1),

    // Lower
    SEC_TO_NANO("seconds to nano", -3),
    MSEC_TO_NANO("milli to nano", -2),
    USEC_TO_NANO("micro to nano", -1),
    SEC_TO_USEC("seconds to micro", -2),
    MSEC_TO_USEC("milli to micro", -1),
    SEC_TO_MSEC("seconds to milli", -1);
}

private const val PREVIOUS_DEGREE = 1000.0
private const val NEXT_DEGREE = 0.001
private const val BASE_DATEFORMAT = "HH:mm:ss.SSS"
private val dateTimeFormatter = DateTimeFormatter.ofPattern(BASE_DATEFORMAT)

val currentTime: String
    get() {
        val date = LocalDateTime.now()
        return dateTimeFormatter.format(date)
    }

fun convertTime(time: Long, convertNtimes: ConvertNtimes): Double {
    var n = convertNtimes.n
    val res: Double
    if (n < 0) {
        n *= -1
        res = convertLowerDegreeNtimes(time, n)
    } else {
        res = convertUpperDegreeNtimes(time, n)
    }
    return res
}

fun convertTime(time: Double, convertNtimes: ConvertNtimes): Double {
    var n = convertNtimes.n
    val res: Double
    if (n < 0) {
        n *= -1
        res = convertLowerDegreeNtimes(time, n)
    } else {
        res = convertUpperDegreeNtimes(time, n)
    }
    return res
}

private fun convertUpperDegreeNtimes(time: Long, n: Int): Double {
    var res = time.toDouble()
    for (i in 0 until n) {
        res *= NEXT_DEGREE
    }
    return res
}

private fun convertUpperDegreeNtimes(time: Double, n: Int): Double {
    var res = time
    for (i in 0 until n) {
        res *= NEXT_DEGREE
    }
    return res
}

private fun convertLowerDegreeNtimes(time: Long, n: Int): Double {
    var res = time.toDouble()
    for (i in 0 until n) {
        res *= PREVIOUS_DEGREE
    }
    return res
}

private fun convertLowerDegreeNtimes(time: Double, n: Int): Double {
    var res = time
    for (i in 0 until n) {
        res *= PREVIOUS_DEGREE
    }
    return res
}