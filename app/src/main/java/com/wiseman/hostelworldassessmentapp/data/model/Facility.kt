package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class FacilityDto(
    @Json(name = "facilities")
    val facilities: List<FacilityX>?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
)