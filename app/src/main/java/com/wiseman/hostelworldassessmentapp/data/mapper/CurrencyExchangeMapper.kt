package com.wiseman.hostelworldassessmentapp.data.mapper

import com.wiseman.hostelworldassessmentapp.data.model.CurrencyExchangeRatesDto
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.model.ExchangeRates

/**
 * Converts a [CurrencyExchangeRatesDto] object to a [CurrencyExchangeRates] domain object.
 *
 * This function maps the properties of the DTO to the corresponding properties of the
 * domain model, including handling the optional `rates` property and converting
 * currency rates to a [ExchangeRates] object.
 * the current supported currency conversion are EUR, USD and GBP
 *
 * @receiver The [CurrencyExchangeRatesDto] object to convert.
 * @return A [CurrencyExchangeRates] object representing the converted data.
 */

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