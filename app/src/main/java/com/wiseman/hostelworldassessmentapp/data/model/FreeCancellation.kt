package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class FreeCancellationDto(
    @Json(name = "description")
    val description: String?,
    @Json(name = "label")
    val label: String?
)