package com.neocryptolabs.core.service.bithumb

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import io.ipfs.multibase.binary.Base64
import org.apache.commons.codec.binary.Hex
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class BithumbApiClient(private var apiKey: String, private var apiSecret: String) {
    private var apiUrl = "https://api.bithumb.com"

    /**
     * ������ �ð��� ns�� �����Ѵ�.(1/1,000,000,000 ��)
     *
     * @return int
     */
    private fun usecTime(): String {
        /*
		long start = System.nanoTime();
		// do stuff
		long nanoseconds = System.nanoTime();
		long microseconds = TimeUnit.NANOSECONDS.toMicros(nanoseconds);
		long seconds = TimeUnit.NANOSECONDS.toSeconds(nanoseconds);

		int elapsedTime = (int) (microseconds + seconds);

		System.out.println("elapsedTime ==> " + microseconds + " : " + seconds);
		*/
        return System.currentTimeMillis().toString()
    }

    private fun request(strHost: String, strMemod: String, rgParams: HashMap<String, String>, httpHeaders: HashMap<String, String>): String {
        var response = ""

        // SSL ����
        if (strHost.startsWith("https://")) {
            val requestBithumb: BithumbHttpRequest = BithumbHttpRequest.get(strHost)
            // Accept all certificates
            requestBithumb.trustAllCerts()
            // Accept all hostnames
            requestBithumb.trustAllHosts()
        }
        if (strMemod.toUpperCase() == "HEAD") {
        } else {
            var requestBithumb: BithumbHttpRequest? = null

            // POST/GET ����
            if (strMemod.toUpperCase() == "POST") {
                requestBithumb = BithumbHttpRequest(strHost, "POST")
                requestBithumb.readTimeout(10000)
//                println("POST ==> " + requestBithumb.url())
                if (httpHeaders != null && httpHeaders.isNotEmpty()) {
                    httpHeaders["api-client-type"] = "2"
                    requestBithumb.headers(httpHeaders)
//                    println(httpHeaders.toString())
                }
                if (rgParams != null && rgParams.isNotEmpty()) {
                    requestBithumb.form(rgParams)
//                    println(rgParams.toString())
                }
            } else {
                requestBithumb = BithumbHttpRequest.get(strHost
                        + BithumbUtil.mapToQueryString(rgParams))
                requestBithumb.readTimeout(10000)
//                println("Response was: $response")
            }
            response = if (requestBithumb.ok()) {
                requestBithumb.body()
            } else {
                ("error : " + requestBithumb.code().toString() + ", message : "
                        + requestBithumb.body())
            }
            requestBithumb.disconnect()
        }
        return response
    }

    private fun getHttpHeaders(endpoint: String, rgData: HashMap<String, String>, apiKey: String, apiSecret: String): HashMap<String, String> {
        var strData: String = BithumbUtil.mapToQueryString(rgData).replace("?", "")
        val nNonce = usecTime()
        strData = strData.substring(0, strData.length - 1)
//        println("1 : $strData")
        strData = encodeURIComponent(strData)!!
        val array = HashMap<String, String>()
        val str = "$endpoint;$strData;$nNonce"
        //String str = "/info/balance;order_currency=BTC&payment_currency=KRW&endpoint=%2Finfo%2Fbalance;272184496";
        val encoded = asHex(hmacSha512(str, apiSecret))
//        println("strData was: $str")
//        println("apiSecret was: $apiSecret")
        array["Api-Key"] = apiKey
        array["Api-Sign"] = encoded
        array["Api-Nonce"] = nNonce
        return array
    }

    fun callApi(endpoint: String, params: HashMap<String, String>?): String {
        var rgResultDecode = ""
        val rgParams = HashMap<String, String>()
        rgParams["endpoint"] = endpoint
        if (params != null) {
            rgParams.putAll(params)
        }
        val apiHost = apiUrl + endpoint
        val httpHeaders = getHttpHeaders(endpoint, rgParams, apiKey, apiSecret)
        rgResultDecode = request(apiHost, "POST", rgParams, httpHeaders)
        if (!rgResultDecode.startsWith("error")) {
            val result: HashMap<String, Any>
            try {
                result = ObjectMapper().readValue(rgResultDecode,
                        object : TypeReference<HashMap<String, Any>>() {})
//                println(result["status"])
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return rgResultDecode
    }

    companion object {
        fun encodeURIComponent(s: String?): String? {
            var result: String? = null
            result = try {
                URLEncoder.encode(s, "UTF-8")
                        .replace("\\+".toRegex(), "%20")
                        .replace("\\%21".toRegex(), "!")
                        .replace("\\%27".toRegex(), "'")
                        .replace("\\%28".toRegex(), "(")
                        .replace("\\%29".toRegex(), ")")
                        .replace("\\%26".toRegex(), "&")
                        .replace("\\%3D".toRegex(), "=")
                        .replace("\\%7E".toRegex(), "~")
            } // This exception should never occur.
            catch (e: UnsupportedEncodingException) {
                s
            }
            return result
        }

        private const val DEFAULT_ENCODING = "UTF-8"
        private const val HMAC_SHA512 = "HmacSHA512"
        fun hmacSha512(value: String, key: String): ByteArray {
            return try {
                val keySpec = SecretKeySpec(
                        key.toByteArray(charset(DEFAULT_ENCODING)),
                        HMAC_SHA512)
                val mac = Mac.getInstance(HMAC_SHA512)
                mac.init(keySpec)
                val macData = mac.doFinal(value.toByteArray())

                //return mac.doFinal(value.getBytes(DEFAULT_ENCODING));
                Hex().encode(macData)
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            } catch (e: InvalidKeyException) {
                throw RuntimeException(e)
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException(e)
            }
        }

        fun asHex(bytes: ByteArray?): String {
            return String(Base64.encodeBase64(bytes))
        }
    }
}