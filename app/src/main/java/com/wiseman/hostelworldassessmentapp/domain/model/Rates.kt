package com.wiseman.hostelworldassessmentapp.domain.model

data class CurrencyExchangeRates(
    val baseCurrency: String?,
    val currencyRates: ExchangeRates?
)

data class ExchangeRates(
    val rates:Map<String, Double?> = emptyMap()
)