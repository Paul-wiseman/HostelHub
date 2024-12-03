package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class NightsStay(
    @Json(name = "Min")
    val min: Int?
)