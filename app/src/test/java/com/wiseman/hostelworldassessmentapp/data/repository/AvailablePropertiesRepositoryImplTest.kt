package com.wiseman.hostelworldassessmentapp.data.repository

import android.content.Context
import com.wiseman.hostelworldassessmentapp.data.mapper.toAvailableProperties
import com.wiseman.hostelworldassessmentapp.data.mapper.toCurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.data.source.remote.HostelApiService
import com.wiseman.hostelworldassessmentapp.util.ErrorMessages.NETWORK_ERROR
import com.wiseman.hostelworldassessmentapp.util.HostelWorldException
import com.wiseman.hostelworldassessmentapp.util.NetworkUtil
import com.wiseman.hostelworldassessmentapp.util.TestDataFactory
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AvailablePropertiesRepositoryImplTest {

    private val hostelApiService: HostelApiService = mockk()
    private val networkUtil: NetworkUtil = mockk()
    private val context: Context = mockk()
    private lateinit var repository: AvailablePropertiesRepositoryImpl


    @BeforeEach
    fun setUp() {
        repository = AvailablePropertiesRepositoryImpl(hostelApiService, context, networkUtil)
    }

    @Test
    fun fetchAvailablePropertiesShouldReturnResult() {
        // Arrange
        val availableProperties = TestDataFactory.getAvailablePropertiesDto()
        every { hostelApiService.fetchAvailableProperties() } returns Single.just(
            availableProperties
        )
        every { networkUtil.isInternetAvailable(context) } returns true

        repository.fetchAvailableProperties() //  Act
            .observeOn(Schedulers.io())
            .test()
            .await()
            .assertValues(availableProperties.toAvailableProperties()) // Assert
            .assertComplete()
    }


    @Test
    fun fetchCurrencyExchangeRateShouldReturnResult() {
        // Arrange
        val exchangeRate = TestDataFactory.getCurrencyExchangeRatesDto()
        every { hostelApiService.fetchCurrencyExchangeRate() } returns Single.just(exchangeRate)
        every { networkUtil.isInternetAvailable(context) } returns true

        // Act
        repository.fetchCurrencyExchangeRate()
            .observeOn(Schedulers.io())
            .test()
            .await()
            .assertValues(exchangeRate.toCurrencyExchangeRates()) // Assert
            .assertComplete()

    }

    @Test
    fun shouldReturnExceptionWhenInternetConnectivityIsNotAvailable() {
        // Arrange
        every { networkUtil.isInternetAvailable(context) } returns false

        // Act
        repository.fetchAvailableProperties()
            .observeOn(Schedulers.io())
            .test()
            .await()
            .assertError(HostelWorldException.NetworkError::class.java) // assert
    }


}