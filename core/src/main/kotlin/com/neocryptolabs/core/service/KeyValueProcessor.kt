package com.neocryptolabs.core.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils
import java.security.Key
import javax.annotation.PostConstruct
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Service
@ConditionalOnExpression("!T(org.springframework.util.StringUtils).isEmpty('\${app.key:}') && !T(org.springframework.util.StringUtils).isEmpty('\${app.value:}')")
class KeyValueProcessor {

    val cipher = "AES/CBC/PKCS5Padding"

    @Value("\${app.key:}")
    lateinit var key: String
    lateinit var keySpec: Key

    @Value("\${app.value:}")
    lateinit var value: String
    lateinit var valueSpec: Key

    @PostConstruct
    fun init() {
        key = key.substring(0, 16)
        val keyBytes = ByteArray(16);
        var b = key.toByteArray()
        var len = b.size
        if (len > keyBytes.size) {
            len = keyBytes.size
        }
        System.arraycopy(b, 0, keyBytes, 0, len)
        this.keySpec = SecretKeySpec(keyBytes, "AES")

        value = value.substring(0, 16)
        val valueBytes = ByteArray(16);
        b = value.toByteArray()
        len = b.size
        if (len > valueBytes.size) {
            len = valueBytes.size
        }
        System.arraycopy(b, 0, valueBytes, 0, len)
        this.valueSpec = SecretKeySpec(valueBytes, "AES")
    }

    fun pushKey(str: String): String {
        val c = Cipher.getInstance(cipher)
        c.init(Cipher.ENCRYPT_MODE, keySpec, IvParameterSpec(key.toByteArray()))
        val encrypted = c.doFinal(str.toByteArray(Charsets.UTF_8))
        return Base64Utils.encodeToUrlSafeString(encrypted)
    }

    fun pollKey(str: String): String {
        val c = Cipher.getInstance(cipher)
        c.init(Cipher.DECRYPT_MODE, keySpec, IvParameterSpec(key.toByteArray()))
        return String(c.doFinal(Base64Utils.decodeFromUrlSafeString(str)), Charsets.UTF_8)
    }

    fun pushValue(str: String): String {
        val c = Cipher.getInstance(cipher)
        c.init(Cipher.ENCRYPT_MODE, valueSpec, IvParameterSpec(value.toByteArray()))
        val encrypted = c.doFinal(str.toByteArray(Charsets.UTF_8))
        return Base64Utils.encodeToUrlSafeString(encrypted)
    }

    fun pollValue(str: String): String {
        val c = Cipher.getInstance(cipher)
        c.init(Cipher.DECRYPT_MODE, valueSpec, IvParameterSpec(value.toByteArray()))
        return String(c.doFinal(Base64Utils.decodeFromUrlSafeString(str)), Charsets.UTF_8)
    }
}