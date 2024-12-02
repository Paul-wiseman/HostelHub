package com.wiseman.hostelworldassessmentapp.data.source.remote

import com.wiseman.hostelworldassessmentapp.data.model.AvailablePropertiesDto
import com.wiseman.hostelworldassessmentapp.data.model.CurrencyExchangeRatesDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface HostelApiService {
    @GET(HostelWorldEndpoints.ALL_AVAILABLE_PROPERTIES)
    fun getAllProperties(): Single<AvailablePropertiesDto>

    @GET(HostelWorldEndpoints.RATES)
    fun getCurrencyExchangeRate():Single<CurrencyExchangeRatesDto>
}