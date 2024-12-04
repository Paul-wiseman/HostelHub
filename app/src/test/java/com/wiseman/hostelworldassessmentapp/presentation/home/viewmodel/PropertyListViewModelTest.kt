package com.wiseman.hostelworldassessmentapp.presentation.home.viewmodel

import app.cash.turbine.test
import com.wiseman.hostelworldassessmentapp.data.mapper.toAvailableProperties
import com.wiseman.hostelworldassessmentapp.data.mapper.toCurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
import com.wiseman.hostelworldassessmentapp.presentation.home.state.UiState
import com.wiseman.hostelworldassessmentapp.util.TestDataFactory
import com.wiseman.hostelworldassessmentapp.util.exception.ErrorMessages.NETWORK_ERROR
import com.wiseman.hostelworldassessmentapp.util.exception.HostelWorldException
import com.wiseman.hostelworldassessmentapp.util.rx.SchedulerProvider
import com.wiseman.hostelworldassessmentapp.util.testScheduler
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PropertyListViewModelTest {

    private val mockRepository: AvailablePropertiesRepository = mockk()
    private lateinit var viewModel: PropertyListViewModel
    private val testAvailablePropertiesData =
        TestDataFactory.getAvailablePropertiesDto().toAvailableProperties()
    private val testExchangeRatesData =
        TestDataFactory.getCurrencyExchangeRatesDto().toCurrencyExchangeRates()
    private val mockSchedulers: SchedulerProvider = testScheduler
    private val dispatcher = UnconfinedTestDispatcher()
    private val scope = TestScope(dispatcher)

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }


    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun getAllAvailablePropertiesShouldEmitLoadingAndThenSuccess(): Unit = runBlocking {
        every { mockRepository.fetchAvailableProperties() } returns Single.just(
            testAvailablePropertiesData
        )
        every { mockRepository.fetchCurrencyExchangeRate() } returns Single.just(
            testExchangeRatesData
        )

        viewModel = PropertyListViewModel(mockRepository, mockSchedulers)
        scope.launch {
            viewModel.state.test {
                assertEquals(awaitItem().state, UiState.Loading)
                assertEquals(awaitItem().state, UiState.Success)
                awaitComplete()
            }
        }
    }


    @Test
    fun getAllAvailablePropertiesShouldEmitLoadingAndThenError(): Unit = runBlocking {
        every { mockRepository.fetchCurrencyExchangeRate() } returns Single.error(HostelWorldException.NetworkError(NETWORK_ERROR))
        every { mockRepository.fetchAvailableProperties() } returns Single.error(HostelWorldException.NetworkError(NETWORK_ERROR))

        viewModel = PropertyListViewModel(mockRepository, mockSchedulers)
        scope.launch {
            viewModel.state.test {
                assertEquals(awaitItem().state, UiState.Loading)
                assertEquals(awaitItem().state, UiState.Error)
                assertEquals(awaitItem().error, NETWORK_ERROR)
                awaitComplete()
            }
        }
    }
}