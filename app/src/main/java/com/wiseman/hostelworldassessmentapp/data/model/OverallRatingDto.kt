package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class OverallRatingDto(
    @Json(name = "numberOfRatings")
    val numberOfRatings: String?,
    @Json(name = "overall")
    val overall: Int?
)