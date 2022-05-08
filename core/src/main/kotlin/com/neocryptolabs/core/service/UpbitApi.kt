package com.neocryptolabs.core.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.neocryptolabs.core.model.Accounts
import com.neocryptolabs.core.model.Orderbook
import com.neocryptolabs.core.model.Orders
import com.neocryptolabs.core.util.floorEightDigit
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import javax.annotation.PostConstruct


@Service
class UpbitApi(private val restTemplate: RestTemplate, private val objectMapper: ObjectMapper) {
    @Value("\${app.exchanger.upbit.access-key:}")
    lateinit var accessKey: String

    @Value("\${app.exchanger.upbit.secret-key:}")
    lateinit var secretKey: String

    lateinit var algorithm: Algorithm

    private val lastApiVersion = "1.2.2"

    @PostConstruct
    fun init() {
        algorithm = Algorithm.HMAC256(secretKey)
    }

    fun getAccounts(): List<Accounts> {
        val uri = UriComponentsBuilder.fromUriString("https://api.upbit.com/v1/accounts")

        val algorithm = Algorithm.HMAC256(secretKey)
        val jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm)

        val authenticationToken = "Bearer $jwtToken"

        val body = getRequestWithAuth(uri.toUriString(), authenticationToken)
//        println(body)
        val result = objectMapper.readValue(body, object : TypeReference<List<Accounts>>() {})
//        println(result)
        return result
    }

    fun getOrder(uuid: String): Orders {
        val uri = UriComponentsBuilder.fromUriString("https://api.upbit.com/v1/order")

        val queryElements = ArrayList<String>()
        queryElements.add("uuid=$uuid")
        val queryString = java.lang.String.join("&", *queryElements.toTypedArray())

        val md = MessageDigest.getInstance("SHA-512")
        md.update(queryString.toByteArray(charset("UTF-8")))

        val queryHash = String.format("%0128x", BigInteger(1, md.digest()))

        val algorithm = Algorithm.HMAC256(secretKey)
        val jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm)

        val authenticationToken = "Bearer $jwtToken"
        val body = getRequestWithAuth(uri.toUriString() + "?" + queryString, authenticationToken)
//        println(body)
        val result = objectMapper.readValue(body, Orders::class.java)
//        println(result)
        return result
    }

    fun cancelOrders(uuid: String): Boolean {
        val params = hashMapOf(
                "uuid" to uuid,
        )
        return deleteOrders(params) != null
    }

    fun buyTokenLimit(market: String, basePrice: Int, price: Int): Orders {
        val params = hashMapOf(
                "market" to market,
                "side" to "bid",
                "price" to basePrice.toString(),
                "volume" to floorEightDigit((price * 1.0) / basePrice).toString(),
                "ord_type" to "limit"
        )
        return postOrders(params)
    }

    fun buyToken(market: String, price: String): Orders {
        val params = hashMapOf(
                "market" to market,
                "side" to "bid",
                "price" to price,
                "ord_type" to "price"
        )
        return postOrders(params)
    }

    fun sellTokenLimit(market: String, price: Int, volume: Double): Orders {
        val params = hashMapOf(
                "market" to market,
                "side" to "ask",
                "price" to price.toString(),
                "volume" to volume.toString(),
                "ord_type" to "limit"
        )
        return postOrders(params)
    }

    fun sellToken(market: String, volume: String): Orders {
        val params = hashMapOf(
                "market" to market,
                "side" to "ask",
                "volume" to volume,
                "ord_type" to "market"
        )
        return postOrders(params)
    }

    fun postOrders(params: HashMap<String, String>): Orders {
        val uri = UriComponentsBuilder.fromUriString("https://api.upbit.com/v1/orders")

        val params2 = LinkedMultiValueMap<String, String>()
        for ((k, v) in params.entries) {
            params2[k] = v
        }

        val queryElements = ArrayList<String>()
        for ((key, value) in params) {
            queryElements.add("$key=$value")
        }
        val queryString = java.lang.String.join("&", *queryElements.toTypedArray())

        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        md.update(queryString.toByteArray(charset("UTF-8")))

        val queryHash = java.lang.String.format("%0128x", BigInteger(1, md.digest()))

        val algorithm = Algorithm.HMAC256(secretKey)
        val jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm)

        val authenticationToken = "Bearer $jwtToken"

        val bodyString = objectMapper.writeValueAsString(params)
//        println("BODY: $bodyString")

        val body = postRequest(uri.toUriString(), authenticationToken, bodyString)
//        println(body)
        val result = objectMapper.readValue(body, Orders::class.java)
//        println(result)
        return result
    }


    fun deleteOrders(params: HashMap<String, String>): Orders {
        val uri = UriComponentsBuilder.fromUriString("https://api.upbit.com/v1/order")

        val queryElements = ArrayList<String>()
        for ((key, value) in params) {
            queryElements.add("$key=$value")
        }
        val queryString = java.lang.String.join("&", *queryElements.toTypedArray())

        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        md.update(queryString.toByteArray(charset("UTF-8")))

        val queryHash = java.lang.String.format("%0128x", BigInteger(1, md.digest()))

        val algorithm = Algorithm.HMAC256(secretKey)
        val jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm)

        val authenticationToken = "Bearer $jwtToken"

        val bodyString = objectMapper.writeValueAsString(params)
//        println("BODY: $bodyString")

        val body = deleteRequest(uri.toUriString() + "?" + queryString, authenticationToken)
//        println(body)
        val result = objectMapper.readValue(body, Orders::class.java)
//        println(result)
        return result
    }

    fun getOrderbook(markets: List<String>): List<Orderbook> {
        val uri = UriComponentsBuilder.fromUriString("https://api.upbit.com/v1/orderbook")
        for (market in markets) {
            uri.queryParam("markets", market)
        }
        val body = getRequest(uri.toUriString())
        val results: List<Orderbook> = objectMapper.readValue(body, object : TypeReference<List<Orderbook>>() {})
        return results
    }

    fun deleteRequest(url: String, authHeader: String): String {
        try {
//            println(url)
            val headers = HttpHeaders()
            headers.set("Content-Type", "application/json")
            headers.add("Authorization", authHeader)
            val entity = HttpEntity(null, headers)

            val res = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    entity,
                    String::class.java
            )

            return res.body
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }
    fun postRequest(url: String, authHeader: String, body: String): String {
        try {
            val headers = HttpHeaders()
            headers.set("Content-Type", "application/json")
            headers.add("Authorization", authHeader)
            val entity = HttpEntity(body, headers)

            val res = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String::class.java
            )

            return res.body
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }

    fun getRequest(url: String): String {
        try {
            val headers = HttpHeaders()
            headers.set("Content-Type", "application/json")
            headers.add("Authorization", buildAuthHeader())
            val entity = HttpEntity<String>(headers)

            val res = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String::class.java
            )

            return res.body
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }

    fun getRequestWithAuth(url: String, authHeader: String): String {
        try {
//            println("Request to ${url}")
            val headers = HttpHeaders()
            headers.set("Content-Type", "application/json")
            headers.add("Authorization", authHeader)
            val entity = HttpEntity<String>(headers)

            val res = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String::class.java
            )

            return res.body
        } catch (e: IOException) {
            e.printStackTrace()
            throw e
        }
    }

    private fun buildAuthHeader(): String {
        val jwtToken: String = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .sign(algorithm)
        return "Bearer $jwtToken"
    }
}