package com.neocryptolabs.core.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal
import java.sql.Timestamp

@JsonIgnoreProperties(ignoreUnknown = true)
data class Orderbook(
        val market: String,
        val timestamp: Timestamp,
        val totalAskSize: BigDecimal,
        val totalBidSize: BigDecimal,
        val orderbookUnits: List<OrderbookUnit>
)

data class OrderbookUnit(
        val askPrice: BigDecimal,
        val bidPrice: BigDecimal,
        val askSize: BigDecimal,
        val bidSize: BigDecimal,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Orders(
        val uuid: String,
        val side: String,
        val ordType: String,
        val price: String?,
        val avgPrice: String?,
        val state: String,
        val market: String,
        val createdAt: String,
        val volume: String?,
        val remainingVolume: String?,
        val reservedFee: String,
        val remainingFee: String,
        val paidFee: String,
        val locked: String,
        val executedVolume: String,
        val tradesCount: Int,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Accounts(
        val currency: String?,
        val balance: String?,
        val locked: String?,
        val avgBuyPrice: String?,
        val avgBuyPriceModified: Boolean?,
        val unitCurrency: String?
)