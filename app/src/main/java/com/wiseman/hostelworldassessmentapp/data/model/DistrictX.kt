package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class DistrictX(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)