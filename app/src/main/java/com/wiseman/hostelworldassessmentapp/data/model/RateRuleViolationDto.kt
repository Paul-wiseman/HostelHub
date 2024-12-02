package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class RateRuleViolationDto(
    @Json(name = "NightsStay")
    val nightsStay: NightsStay?
)