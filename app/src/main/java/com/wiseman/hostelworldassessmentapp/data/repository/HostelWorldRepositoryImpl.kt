package com.wiseman.hostelworldassessmentapp.data.repository

import android.content.Context
import com.wiseman.hostelworldassessmentapp.data.mapper.toCurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.data.source.remote.HostelApiService
import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
import com.wiseman.hostelworldassessmentapp.util.ErrorMessages.NETWORK_ERROR
import com.wiseman.hostelworldassessmentapp.util.HostelWorldException
import com.wiseman.hostelworldassessmentapp.util.NetworkUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AvailablePropertiesRepositoryImpl @Inject constructor(
    private val hostelApiService: HostelApiService,
    @ApplicationContext private val context: Context,
    private val networkUtil: NetworkUtil
) : AvailablePropertiesRepository {

    override fun fetchAvailableProperties(): Single<AvailableProperties> {
        return makeNetworkRequest { hostelApiService.fetchAvailableProperties().map { it.toCurrencyExchangeRates() } }
    }

    override fun fetchCurrencyExchangeRate(): Single<CurrencyExchangeRates> {
        return makeNetworkRequest {
            hostelApiService.fetchCurrencyExchangeRate().map { it.toCurrencyExchangeRates() }
        }
    }

    private fun <T : Any> makeNetworkRequest(request: () -> Single<T>): Single<T> {
        return if (networkUtil.isInternetAvailable(context)) {
            request().subscribeOn(Schedulers.io())
        } else Single.error(HostelWorldException.NetworkError(NETWORK_ERROR))
    }


}