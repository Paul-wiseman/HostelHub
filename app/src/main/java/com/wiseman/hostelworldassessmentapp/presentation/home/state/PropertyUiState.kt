package com.wiseman.hostelworldassessmentapp.presentation.home.state

import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.model.Location
import com.wiseman.hostelworldassessmentapp.domain.model.Property

data class PropertyUiState(
    val location: Location? = null,
    val properties: List<Property>? = null,
    val currentExchangeRate: CurrencyExchangeRates? = null,
    val error: String? = null,
    val state: UiState = UiState.Loading
)

sealed class UiState {
    data object Loading : UiState()
    data object Error : UiState()
    data object Success : UiState()
}
