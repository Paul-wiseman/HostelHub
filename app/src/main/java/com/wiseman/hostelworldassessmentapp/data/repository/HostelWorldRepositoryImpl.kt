package com.wiseman.hostelworldassessmentapp.data.repository

import android.content.Context
import com.wiseman.hostelworldassessmentapp.data.mapper.toCurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.data.mapper.toAvailableProperties
import com.wiseman.hostelworldassessmentapp.data.source.remote.HostelApiService
import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
import com.wiseman.hostelworldassessmentapp.util.exception.ErrorMessages.NETWORK_ERROR
import com.wiseman.hostelworldassessmentapp.util.exception.HostelWorldException
import com.wiseman.hostelworldassessmentapp.util.NetworkUtil
import com.wiseman.hostelworldassessmentapp.util.rx.SchedulerProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Implementation of the [AvailablePropertiesRepository] interface.
 *
 * This class is responsible for fetching available properties and currency exchange rates
 * from the network using the [HostelApiService]. It handles network connectivity and
 * error handling.
 *
 * @property hostelApiService The API service used to make network requests.
 * @property context The application context.
 * @property schedulerProvider Provides schedulers for asynchronous operations.
 * @property networkUtil Utility class for checking network connectivity.
 */

class AvailablePropertiesRepositoryImpl @Inject constructor(
    private val hostelApiService: HostelApiService,
    @ApplicationContext private val context: Context,
    private val schedulerProvider: SchedulerProvider,
    private val networkUtil: NetworkUtil
) : AvailablePropertiesRepository {

    override fun fetchAvailableProperties(): Single<AvailableProperties> {
        return makeNetworkRequest {
            hostelApiService.fetchAvailableProperties().map { it.toAvailableProperties() }
        }
    }

    override fun fetchCurrencyExchangeRate(): Single<CurrencyExchangeRates> {
        return makeNetworkRequest {
            hostelApiService.fetchCurrencyExchangeRate().map { it.toCurrencyExchangeRates() }
        }
    }

    private fun <T : Any> makeNetworkRequest(request: () -> Single<T>): Single<T> {
        return if (networkUtil.isInternetAvailable(context)) {
            try {
                request().subscribeOn(schedulerProvider.io())
            } catch (e: Exception) {
                Single.error(HostelWorldException.ParsingError(e.message.toString()))
            }
        } else Single.error(HostelWorldException.NetworkError(NETWORK_ERROR))
    }


}