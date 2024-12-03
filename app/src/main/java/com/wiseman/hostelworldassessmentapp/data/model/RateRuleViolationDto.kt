package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class RateRuleViolationDto(
    @Json(name = "NightsStay")
    val nightsStay: NightsStay?
)