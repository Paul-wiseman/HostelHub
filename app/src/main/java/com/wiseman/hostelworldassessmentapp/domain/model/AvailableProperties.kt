package com.wiseman.hostelworldassessmentapp.domain.model

data class AvailableProperties(
    val location: Location?,
    val properties: List<Property>?
)

data class Location(
    val country: String?,
    val name: String?,
    val idCountry: Int?,
)

data class Facility(
    val facilities: List<Amenity>?,
    val name: String?
)

data class Amenity(
    val name: String?
)

data class ImagesGallery(
    val imageUrl: String
)

data class FreeCancellation(
    val description: String?,
    val label: String?
)

data class OverallRating(
    val numberOfRatings: String,
    val overall: Int
)

data class LowestPricePerNight(
    val currency: String,
    val value: String?
)

data class Distance(
    val units: String?,
    val value: Double?
)

data class Property(
    val address1: String?,
    val address2: String?,
    val facilities: List<Facility>?,
    val freeCancellation: FreeCancellation?,
    val freeCancellationAvailable: Boolean?,
    val freeCancellationAvailableUntil: String?,
    val id: Int?,
    val imagesGallery: List<ImagesGallery>,
    val isFeatured: Boolean?,
    val isPromoted: Boolean?,
    val name: String?,
    val overallRating: OverallRating,
    val overview: String?,
    val position: Int?,
    val type: String?,
    val distance: Distance,
    val veryPopular: Boolean?,
    val lowestPricePerNight: LowestPricePerNight?,
)