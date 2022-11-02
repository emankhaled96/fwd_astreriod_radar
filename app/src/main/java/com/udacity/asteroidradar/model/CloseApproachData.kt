package com.udacity.asteroidradar.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CloseApproachData(
    @Json(name = "close_approach_date") val closeApproachDate: String,
    @Json(name = "close_approach_date_full") val closeApproachDateFull: String,
    @Json(name = "epoch_date_close_approach") val epochDate: Long,
    @Json(name = "relative_velocity") val relativeVelocity: RelativeVelocity,
    @Json(name = "miss_distance") val missDistance: Distance,
    @Json(name = "orbiting_body") val orbitingBody: String,

    ) : Parcelable
