package com.wiseman.hostelworldassessmentapp.presentation.home.state

import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.model.Location
import com.wiseman.hostelworldassessmentapp.domain.model.Property
/**
 * Data class representing the UI state for the property list screen.
 *
 * This class holds the data and state information that is displayed on the property
 * list screen, including the location, properties, current exchange rate, error
 * message, and the current UI state.
 *
 * @property location The location information.
 * @property properties The list of available properties.
 * @property currentExchangeRate The current exchange rate.
 * @property error The error message, if any.
 * @property state The current UI state (loading, error, or success).
 */

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

