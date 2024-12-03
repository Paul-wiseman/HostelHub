package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class AvailablePropertiesDto(
    @Json(name = "filterData")
    val filterData: FilterDataDto?,
    @Json(name = "location")
    val location: LocationDto?,
    @Json(name = "locationEn")
    val locationEn: LocationEnDto?,
    @Json(name = "pagination")
    val pagination: Pagination?,
    @Json(name = "properties")
    val properties: List<PropertyDto>?,
    @Json(name = "sortOrder")
    val sortOrder: String?
)