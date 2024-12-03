package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyExchangeRatesDto(
    @Json(name = "base")
    val baseCurrency: String?,
    @Json(name = "date")
    val date: String?,
    @Json(name = "historical")
    val historical: Boolean?,
    @Json(name = "rates")
    val rates: ExchangeRatesDto?,
    @Json(name = "success")
    val requestSuccess: Boolean?,
    @Json(name = "timestamp")
    val timestamp: Int?
)