package com.neocryptolabs.core.service.bithumb

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64
import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object BithumbUtil {
    private const val DEFAULT_ENCODING = "UTF-8"
    private const val HMAC_SHA512 = "HmacSHA512"
    fun base64Encode(bytes: ByteArray?): String {
        return Base64.encode(bytes)
    }

    fun hashToString(data: String, key: ByteArray?): String? {
        var result: String? = null
        val sha512_HMAC: Mac
        try {
            sha512_HMAC = Mac.getInstance("HmacSHA512")
            println("key : " + String(key!!))
            val secretkey = SecretKeySpec(key, "HmacSHA512")
            sha512_HMAC.init(secretkey)
            val mac_data = sha512_HMAC.doFinal(data.toByteArray())
            println("hex : " + bin2hex(mac_data))
            result = Base64.encode(mac_data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    fun hmacSha512(value: String, key: String): ByteArray {
        return try {
            val keySpec = SecretKeySpec(
                    key.toByteArray(charset(DEFAULT_ENCODING)), HMAC_SHA512)
            val mac = Mac.getInstance(HMAC_SHA512)
            mac.init(keySpec)
            mac.doFinal(value.toByteArray(charset(DEFAULT_ENCODING)))
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        } catch (e: InvalidKeyException) {
            throw RuntimeException(e)
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException(e)
        }
    }

    fun asHex(bytes: ByteArray?): String {
        return Base64.encode(bytes)
    }

    fun bin2hex(data: ByteArray): String {
        return String.format("%0" + data.size * 2 + "X", BigInteger(1, data))
    }

    fun mapToQueryString(map: HashMap<String, String>): String {
        val string = StringBuilder()
        if (map.size > 0) {
            string.append("?")
        }
        for ((key, value) in map) {
            string.append(key)
            string.append("=")
            string.append(value)
            string.append("&")
        }
        return string.toString()
    }
}
