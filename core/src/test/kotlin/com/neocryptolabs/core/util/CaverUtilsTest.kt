package com.neocryptolabs.core.util

import org.junit.jupiter.api.Test

class CaverUtilsTest {
    @Test
    fun connect() {
        val pair = connectCaver(
                keystore = "{\"address\":\"f8046a8727ff7b571d83f03b4a069a091e7e32f7\",\"keyring\":[[{\"cipher\":\"aes-128-ctr\",\"ciphertext\":\"4b9d965d187821ba88f6efcec113ad1ae8b97a8e731a39f8e6d02c07a0748b9a\",\"cipherparams\":{\"iv\":\"91c2ba66bd487862b06a65ab4cf03c9f\"},\"kdf\":\"scrypt\",\"kdfparams\":{\"dklen\":32,\"n\":262144,\"p\":1,\"r\":8,\"salt\":\"14fe55b67e14f822f21331ba93beb5e4574d2cbfd3b97c9f1bf126adc2de4336\"},\"mac\":\"76a81d74e7355b749f437c72849ee9e7cc06be8bebd4efc757418e8a0f8c14ee\"}]],\"id\":\"b44a9877-b53e-4674-be4d-6c02551e99f5\",\"version\":4}",
                pwd = "aa"
        )
        println(pair.first)
        println(pair.second)
    }
}