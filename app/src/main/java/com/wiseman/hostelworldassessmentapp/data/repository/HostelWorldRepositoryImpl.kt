package com.wiseman.hostelworldassessmentapp.data.repository

import android.content.Context
import com.wiseman.hostelworldassessmentapp.data.mapper.toDomain
import com.wiseman.hostelworldassessmentapp.data.mapper.toDomainModel
import com.wiseman.hostelworldassessmentapp.data.source.remote.HostelApiService
import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
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

    override fun getAvailableProperties(): Single<AvailableProperties> {
        return if (networkUtil.isInternetAvailable(context)) {
            hostelApiService.getAllProperties().map {
                it.toDomainModel()
            }
                .subscribeOn(Schedulers.io())
        } else Single.error(HostelWorldException.NetworkError(NETWORK_ERROR))
    }

    override fun getCurrentExchangeRate(): Single<CurrencyExchangeRates> {
        return if (networkUtil.isInternetAvailable(context)) {
            hostelApiService.getCurrencyExchangeRate().map {
                it.toDomain()
            }
                .subscribeOn(Schedulers.io())
        } else {
            Single.error(HostelWorldException.NetworkError(NETWORK_ERROR))
        }
    }


    private companion object {
        const val NETWORK_ERROR = "No Internet connection"
    }
}