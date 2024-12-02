package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class StayRuleViolationDto(
    @Json(name = "description")
    val description: String?
)