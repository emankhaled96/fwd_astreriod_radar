package com.udacity.asteroidradar.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asteroid(
    @Json(name = "links") val links: Map<String, String>,
    val id: Long,
    @Json(name = "neo_reference_id") val referenceId: String,
    val name: String,
    @Json(name = "nasa_jpl_url") val nasaUrl: String,
    @Json(name = "absolute_magnitude_h") val absoluteMagnitude: Double,
    @Json(name = "estimated_diameter") val estimatedDiameter: Diameters,
    @Json(name = "is_potentially_hazardous_asteroid") val isPotentiallyHazardous: Boolean,
    @Json(name = "close_approach_data") val closeApproachData: List<CloseApproachData>,
    @Json(name = "is_sentry_object") val isSentryObject: Boolean
) : Parcelable