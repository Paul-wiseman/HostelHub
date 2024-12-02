package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class LocationEnDto(
    @Json(name = "city")
    val city: CityDto?,
    @Json(name = "region")
    val region: Any?
)