package com.wiseman.hostelworldassessmentapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
import com.wiseman.hostelworldassessmentapp.presentation.home.HomeScreenViewState
import com.wiseman.hostelworldassessmentapp.presentation.home.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
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
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val _state = MutableStateFlow(HomeScreenViewState())
    val state: StateFlow<HomeScreenViewState> = _state

    init {
        getAllAvailableProperties()
        getCurrentExchangeRate()
    }

    private fun getAllAvailableProperties() {
        withDisposable {
            repository.getAvailableProperties()
                .observeOn(AndroidSchedulers.mainThread())
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

    private fun getCurrentExchangeRate() {
        viewModelScope.launch {
            while (isActive) {
                withDisposable {
                    repository.getCurrentExchangeRate()
                        .observeOn(AndroidSchedulers.mainThread())
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