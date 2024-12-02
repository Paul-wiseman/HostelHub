package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class DistanceDto(
    @Json(name = "units")
    val units: String?,
    @Json(name = "value")
    val value: Double?
)