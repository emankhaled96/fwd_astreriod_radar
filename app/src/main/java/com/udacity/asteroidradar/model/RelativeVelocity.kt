package com.udacity.asteroidradar.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RelativeVelocity(
    @Json(name = "kilometers_per_second")  val kmPerSec : String,
    @Json(name = "kilometers_per_hour")  val kmPerHr : String,
    @Json(name = "miles_per_hour")  val milesPerHr : String,
    ):Parcelable
