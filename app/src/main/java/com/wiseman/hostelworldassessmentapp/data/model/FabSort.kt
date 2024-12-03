package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class FabSortDto(
    @Json(name = "rank1")
    val rank1: Int?,
    @Json(name = "rank2")
    val rank2: Int?,
    @Json(name = "rank3")
    val rank3: Int?,
    @Json(name = "rank4")
    val rank4: Int?,
    @Json(name = "rank5")
    val rank5: Int?,
    @Json(name = "rank6")
    val rank6: Int?,
    @Json(name = "rank7")
    val rank7: Int?,
    @Json(name = "rank8")
    val rank8: Int?,
    @Json(name = "rank9")
    val rank9: Int?
)