package com.neocryptolabs.core.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CalUtilsTest {
    @Test
    fun floorDegreeToMult0() {
        val value = 11057
        println("$value -> ${floorDegreeToMult0(value)}")
        assertThat(floorDegreeToMult0(value) > value)
    }

    @Test
    fun floorThreeDegree() {
        val value = (59450000 * 0.97).toInt()
        println("$value -> ${floorThreeDegree(value)}")
        assertThat(floorThreeDegree(value) > value)
    }
}