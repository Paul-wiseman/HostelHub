package com.wiseman.hostelworldassessmentapp.data.mapper

import com.wiseman.hostelworldassessmentapp.data.model.AvailablePropertiesDto
import com.wiseman.hostelworldassessmentapp.data.model.LocationDto
import com.wiseman.hostelworldassessmentapp.data.model.PropertyDto
import com.wiseman.hostelworldassessmentapp.domain.model.Amenity
import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.Facility
import com.wiseman.hostelworldassessmentapp.domain.model.FreeCancellation
import com.wiseman.hostelworldassessmentapp.domain.model.ImagesGallery
import com.wiseman.hostelworldassessmentapp.domain.model.Location
import com.wiseman.hostelworldassessmentapp.domain.model.LowestPricePerNight
import com.wiseman.hostelworldassessmentapp.domain.model.OverallRating
import com.wiseman.hostelworldassessmentapp.domain.model.Property

fun AvailablePropertiesDto.toAvailableProperties(): AvailableProperties =
    AvailableProperties(
        location = location?.toLocation(),
        properties = properties?.map { it.toProperty() }
    )

fun LocationDto.toLocation(): Location = Location(
    country = city?.country,
    name = city?.name,
    idCountry = city?.idCountry
)

fun PropertyDto.toProperty(): Property =
    Property(
        address1 = address1,
        address2 = address2,
        facilities = facilities?.map { facilityDto ->
            Facility(
                facilities = facilityDto.facilities?.map { amenity ->
                    Amenity(name = amenity.name)
                },
                name = facilityDto.name

            )
        },
        freeCancellation = FreeCancellation(
            description = freeCancellation?.description,
            label = freeCancellation?.label
        ),
        freeCancellationAvailable = freeCancellationAvailable,
        freeCancellationAvailableUntil = freeCancellationAvailableUntil,
        id = id,
        imagesGallery = imagesGallery?.map { imageGallery ->
            ImagesGallery(
                imageUrl = String.format(
                    "https://%s%s",
                    imageGallery.prefix,
                    imageGallery.suffix
                )
            )
        } ?: listOf(),
        isFeatured = isFeatured,
        isPromoted = isPromoted,
        name = name,
        overallRating = OverallRating(
            numberOfRatings = overallRating?.numberOfRatings ?: "0",
            overall = overallRating?.overall ?: 0
        ),
        overview = overview,
        position = position,
        type = type,
        veryPopular = veryPopular,
        lowestPricePerNight = lowestPricePerNight?.currency?.let { currency ->
            LowestPricePerNight(
                currency = currency,
                value = lowestPricePerNight.value
            )
        },
    )
