package com.neocryptolabs.core.service

import com.neocryptolabs.core.model.*
import com.neocryptolabs.core.service.bithumb.BithumbApiClient
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.annotation.PostConstruct


@Service
class BithumbApi(private val restTemplate: RestTemplate, private val objectMapper: ObjectMapper) {
    @Value("\${app.exchanger.bithumb.access-key:}")
    lateinit var accessKey: String

    @Value("\${app.exchanger.bithumb.secret-key:}")
    lateinit var secretKey: String

    lateinit var apiClient: BithumbApiClient

    private val lastApiVersion = ""

    @PostConstruct
    fun init() {
        apiClient = BithumbApiClient(accessKey, secretKey)
    }

    fun getOrderbook(token: String): OrderbookBithumb {
        val url = "https://api.bithumb.com/public/orderbook/${token}_KRW?count=1";
        val res = restTemplate.getForEntity(url, String::class.java)
        return objectMapper.readValue(res.body, OrderbookBithumb::class.java)
    }

    fun getBalance() {
        val params = HashMap<String, String>()
        params["order_currency"] = "BTC"
        params["payment_currency"] = "KRW"
        params["currency"] = "ALL"
        val result = apiClient.callApi("/info/balance", params)
        println(result)
    }

    fun getTicker() {
        val params = HashMap<String, String>()
        params["order_currency"] = "CYCLUB"
        params["payment_currency"] = "KRW"
        val result = apiClient.callApi("/info/ticker", params)
        println(result)
    }

    fun getTransaction() {
        val params = HashMap<String, String>()
        params["order_currency"] = "CYCLUB"
        params["offset"] = "0"
        params["count"] = "50"
        params["searchGb"] = "0"
        params["payment_currency"] = "KRW"
        val result = apiClient.callApi("/info/user_transactions", params)
        println(result)
    }

    // Get All amount
    // NOTE: 소수점 4자리까지 가능
    fun getAccount(token: String): AccountBithumb {
        val params = HashMap<String, String>()
        params["order_currency"] = token
        params["payment_currency"] = "KRW"
        val result = apiClient.callApi("/info/account", params)
        return objectMapper.readValue(result, AccountBithumb::class.java)
    }

    // Observe order status
    fun getOrderDetail(token: String, orderId: String): OrderDetail {
        val params = HashMap<String, String>()
        params["order_currency"] = token
        params["payment_currency"] = "KRW"
        params["order_id"] = orderId
        val result = apiClient.callApi("/info/order_detail", params)
        try {
            return objectMapper.readValue(result, OrderDetail::class.java)
        } catch (e: JsonParseException) {
            return OrderDetail(status = null, OrderDetailData(type = "order_detail", orderStatus = "ERROR", orderCurrency = token))
        }
    }

    // Reserve buy
    fun buyTokenLimit(token: String, amount: Double, price: Int): PlaceOrder {
        val params = HashMap<String, String>()
        params["order_currency"] = token
        params["payment_currency"] = "KRW"
        params["units"] = amount.toString()
        params["price"] = price.toString()
        params["type"] = "bid"
        println(params)
        val result = apiClient.callApi("/trade/place", params)
        return objectMapper.readValue(result, PlaceOrder::class.java)
    }

    // Reserve sell
    fun sellTokenLimit(token: String, amount: Double, price: Int): PlaceOrder {
        val params = HashMap<String, String>()
        params["order_currency"] = token
        params["payment_currency"] = "KRW"
        params["units"] = amount.toString()
        params["price"] = price.toString()
        params["type"] = "ask"
        val result = apiClient.callApi("/trade/place", params)
        return objectMapper.readValue(result, PlaceOrder::class.java)
    }

    // Rebase or Reorder
    fun cancelOrder(token: String, orderId: String): CancelOrder {
        val params = HashMap<String, String>()
        params["order_currency"] = token
        params["payment_currency"] = "KRW"
        params["order_id"] = orderId
        val result = apiClient.callApi("/trade/cancel", params)
        return objectMapper.readValue(result, CancelOrder::class.java)
    }
}