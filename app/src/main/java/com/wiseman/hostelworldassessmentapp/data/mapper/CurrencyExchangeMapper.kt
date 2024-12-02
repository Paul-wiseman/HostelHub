package com.wiseman.hostelworldassessmentapp.data.mapper

import com.wiseman.hostelworldassessmentapp.data.model.CurrencyExchangeRatesDto
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.model.ExchangeRates

fun CurrencyExchangeRatesDto.toCurrencyExchangeRates() = CurrencyExchangeRates(
    baseCurrency = baseCurrency,
    currencyRates = rates?.let {
        ExchangeRates(
            mapOf(
                "USD" to it.USD,
                "EUR" to it.EUR?.toDouble(),
                "GBP" to it.GBP
            )
        )
    }
)