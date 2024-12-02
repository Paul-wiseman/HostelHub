package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class NightsStay(
    @Json(name = "Min")
    val min: Int?
)