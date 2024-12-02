package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class FacilityX(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
)