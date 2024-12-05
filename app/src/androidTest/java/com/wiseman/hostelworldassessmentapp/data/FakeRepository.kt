package com.wiseman.hostelworldassessmentapp.data

import com.wiseman.hostelworldassessmentapp.data.mapper.toAvailableProperties
import com.wiseman.hostelworldassessmentapp.data.mapper.toCurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
import io.reactivex.rxjava3.core.Single
import source.TestDataFactory

class FakeRepository : AvailablePropertiesRepository {

    override fun fetchAvailableProperties(): Single<AvailableProperties> {
        return Single.just(TestDataFactory.getAvailablePropertiesDto().toAvailableProperties())
    }

    override fun fetchCurrencyExchangeRate(): Single<CurrencyExchangeRates> {
        return Single.just(TestDataFactory.getCurrencyExchangeRatesDto().toCurrencyExchangeRates())
    }
}