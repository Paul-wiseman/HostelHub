package com.wiseman.hostelworldassessmentapp.domain.repository

import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import io.reactivex.rxjava3.core.Single

/**
 * Repository interface for accessing available properties and currency exchange rates.
 *
 * This interface defines the methods for fetching available properties and currency
 * exchange rates. Implementations of this interface are responsible for providing
 * the data from a specific source, such as a network API or a local database.
 */
interface AvailablePropertiesRepository {
    fun fetchAvailableProperties(): Single<AvailableProperties>
    fun fetchCurrencyExchangeRate(): Single<CurrencyExchangeRates>
}