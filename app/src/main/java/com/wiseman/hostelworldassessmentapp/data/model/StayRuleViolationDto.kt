package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class StayRuleViolationDto(
    @Json(name = "description")
    val description: String?
)