package com.neocryptolabs.core.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.math.BigInteger

@Service
class GasPriceCollector(private val restTemplate: RestTemplate, private val objectMapper: ObjectMapper) {

	private val url = "http://localhost:8551"
	private val data = "{\"jsonrpc\":\"2.0\",\"method\":\"klay_gasPrice\",\"params\":[],\"id\":1}"

	fun getGasPrice(): BigInteger {
		val resp = objectMapper.readValue(postRequest(url, data), GasPriceResponse::class.java)
		println("Gas Price=${resp.getPrice()}")
		return resp.getPrice()
	}

	private fun postRequest(url: String, body: String): String {
		try {
			val headers = HttpHeaders()
			headers.set("Content-Type", "application/json")
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

	data class GasPriceResponse(
			val jsonrpc: String,
			val id: Int,
			val result: String
	) {
		fun getPrice(): BigInteger =
				BigInteger.valueOf(result.substring(2).toLong(16))
	}
}