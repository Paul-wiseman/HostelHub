package com.wiseman.hostelworldassessmentapp.presentation.home

import com.wiseman.hostelworldassessmentapp.domain.model.Property

sealed class HomeFragmentEvent {
    data class OnItemSelected(val selectedItem:Property):HomeFragmentEvent()
}