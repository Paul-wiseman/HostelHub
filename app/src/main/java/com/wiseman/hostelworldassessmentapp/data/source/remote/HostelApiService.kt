package com.wiseman.hostelworldassessmentapp.data.source.remote

import com.wiseman.hostelworldassessmentapp.data.model.AvailablePropertiesDto
import com.wiseman.hostelworldassessmentapp.data.model.CurrencyExchangeRatesDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * API service interface for interacting with the HostelWorld API.
 *
 * This interface defines the methods for making network requests to the HostelWorld API
 * to fetch available properties and currency exchange rates using Retrofit and RxJava
 */
interface HostelApiService {
    @GET(HostelWorldEndpoints.PROPERTIES_ENDPOINT)
    fun fetchAvailableProperties(): Single<AvailablePropertiesDto>

    @GET(HostelWorldEndpoints.RATES_ENDPOINT)
    fun fetchCurrencyExchangeRate():Single<CurrencyExchangeRatesDto>
}