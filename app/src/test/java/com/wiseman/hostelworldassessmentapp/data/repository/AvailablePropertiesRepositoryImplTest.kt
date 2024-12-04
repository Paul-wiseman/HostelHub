package com.wiseman.hostelworldassessmentapp.data.repository

import android.content.Context
import com.wiseman.hostelworldassessmentapp.data.mapper.toAvailableProperties
import com.wiseman.hostelworldassessmentapp.data.mapper.toCurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.data.source.remote.HostelApiService
import com.wiseman.hostelworldassessmentapp.util.exception.HostelWorldException
import com.wiseman.hostelworldassessmentapp.util.NetworkUtil
import com.wiseman.hostelworldassessmentapp.util.rx.SchedulerProvider
import com.wiseman.hostelworldassessmentapp.util.TestDataFactory
import com.wiseman.hostelworldassessmentapp.util.testScheduler
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class AvailablePropertiesRepositoryImplTest {
    private val mockHostelApiService: HostelApiService = mockk()
    private val mockNetworkUtil: NetworkUtil = mockk()
    private val mockContext: Context = mockk()
    private val mockSchedulers: SchedulerProvider = testScheduler
    private lateinit var repository: AvailablePropertiesRepositoryImpl



    @BeforeEach
    fun setUp() {
        repository = AvailablePropertiesRepositoryImpl(mockHostelApiService, mockContext, mockSchedulers, mockNetworkUtil)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun fetchAvailablePropertiesShouldReturnResult() {
        // Arrange
        val availableProperties = TestDataFactory.getAvailablePropertiesDto()
        every { mockHostelApiService.fetchAvailableProperties() } returns Single.just(
            availableProperties
        )
        every { mockNetworkUtil.isInternetAvailable(mockContext) } returns true

        repository.fetchAvailableProperties() //  Act
            .observeOn(testScheduler.io())
            .test()
            .await()
            .assertValues(availableProperties.toAvailableProperties()) // Assert
            .assertComplete()
    }

    @Test
    fun fetchCurrencyExchangeRateShouldReturnResult() {
        // Arrange
        val exchangeRate = TestDataFactory.getCurrencyExchangeRatesDto()
        every { mockHostelApiService.fetchCurrencyExchangeRate() } returns Single.just(exchangeRate)
        every { mockNetworkUtil.isInternetAvailable(mockContext) } returns true

        // Act
        repository.fetchCurrencyExchangeRate()
            .observeOn(testScheduler.io())
            .test()
            .await()
            .assertValues(exchangeRate.toCurrencyExchangeRates()) // Assert
            .assertComplete()
    }

    @Test
    fun shouldReturnExceptionWhenInternetConnectivityIsNotAvailable() {
        // Arrange
        every { mockNetworkUtil.isInternetAvailable(mockContext) } returns false

        // Act
        repository.fetchAvailableProperties()
            .observeOn(testScheduler.io())
            .test()
            .await()
            .assertError(HostelWorldException.NetworkError::class.java) // assert
    }

}