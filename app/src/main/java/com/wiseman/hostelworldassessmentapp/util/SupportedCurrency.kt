package com.wiseman.hostelworldassessmentapp.util


sealed class SupportedCurrency(val currencyCode: String, val currencySymbol: String) {
    data object EUR : SupportedCurrency(
        "EUR",
        getCurrencySymbolFromCode("EUR")
    )

    data object GBP : SupportedCurrency(
        "GBP",
        getCurrencySymbolFromCode("GBP")
    )

    data object USD : SupportedCurrency(
        "USD",
        getCurrencySymbolFromCode("USD")
    )

}