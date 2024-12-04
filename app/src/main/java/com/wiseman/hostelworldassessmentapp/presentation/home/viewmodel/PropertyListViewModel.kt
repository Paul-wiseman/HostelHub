package com.wiseman.hostelworldassessmentapp.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
import com.wiseman.hostelworldassessmentapp.presentation.home.state.PropertyUiState
import com.wiseman.hostelworldassessmentapp.presentation.home.state.UiState
import com.wiseman.hostelworldassessmentapp.util.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val repository: AvailablePropertiesRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val _state = MutableStateFlow(PropertyUiState())
    val state: StateFlow<PropertyUiState> = _state

    init {
        getAllAvailableProperties()
        getCurrentExchangeRate()
    }

     private fun getAllAvailableProperties() {
        viewModelScope.launch {
            withDisposable {
                repository.fetchAvailableProperties()
                    .observeOn(schedulerProvider.ui())
                    .doOnSubscribe {
                        _state.update { homeScreenViewState ->
                            homeScreenViewState.copy(state = UiState.Loading, error = null)
                        }
                    }
                    .subscribe(
                        { data: AvailableProperties ->
                            _state.update { homeScreenViewState ->
                                homeScreenViewState.copy(
                                    state = UiState.Success,
                                    error = null,
                                    location = data.location,
                                    properties = data.properties
                                )
                            }
                        },
                        { error ->
                            _state.update { homeScreenViewState ->
                                homeScreenViewState.copy(
                                    state = UiState.Error,
                                    error = error.message
                                )
                            }
                        }
                    )
            }
        }
    }

     private fun getCurrentExchangeRate() {
        viewModelScope.launch {
            while (isActive) {
                withDisposable {
                    repository.fetchCurrencyExchangeRate()
                        .observeOn(schedulerProvider.ui())
                        .subscribe(
                            { data: CurrencyExchangeRates ->
                                _state.update { state ->
                                    state.copy(
                                        state = UiState.Success,
                                        error = null,
                                        currentExchangeRate = data,
                                    )
                                }
                            },
                            { error ->
                                _state.update { state ->
                                    state.copy(
                                        state = UiState.Error,
                                        error = error.message
                                    )
                                }
                            }
                        )
                }
                delay(CURRENCY_EXCHANGE_DELAY)
            }
        }
    }

    private inline fun withDisposable(block: () -> Disposable) {
        with(disposable) {
            add(block())
        }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    private companion object {
        const val CURRENCY_EXCHANGE_DELAY =
            10000L // Time to refresh the exchange rate. this can be change based on requirement
    }
}