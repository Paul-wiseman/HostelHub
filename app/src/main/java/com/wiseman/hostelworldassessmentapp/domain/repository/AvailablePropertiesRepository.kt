package com.wiseman.hostelworldassessmentapp.domain.repository

import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import io.reactivex.rxjava3.core.Single

interface AvailablePropertiesRepository {
    fun fetchAvailableProperties(): Single<AvailableProperties>
    fun fetchCurrencyExchangeRate(): Single<CurrencyExchangeRates>
}