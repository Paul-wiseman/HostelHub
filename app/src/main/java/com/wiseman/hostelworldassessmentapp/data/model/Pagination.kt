package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class Pagination(
    @Json(name = "next")
    val next: String?,
    @Json(name = "numberOfPages")
    val numberOfPages: Int?,
    @Json(name = "prev")
    val prev: String?,
    @Json(name = "totalNumberOfItems")
    val totalNumberOfItems: Int?
)