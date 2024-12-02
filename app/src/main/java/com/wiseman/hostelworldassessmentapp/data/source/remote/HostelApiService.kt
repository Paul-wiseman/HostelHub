package com.wiseman.hostelworldassessmentapp.data.source.remote

import com.wiseman.hostelworldassessmentapp.data.model.AvailablePropertiesDto
import com.wiseman.hostelworldassessmentapp.data.model.CurrencyExchangeRatesDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface HostelApiService {
    @GET(HostelWorldEndpoints.PROPERTIES_ENDPOINT)
    fun fetchAvailableProperties(): Single<AvailablePropertiesDto>

    @GET(HostelWorldEndpoints.RATES_ENDPOINT)
    fun fetchCurrencyExchangeRate():Single<CurrencyExchangeRatesDto>
}