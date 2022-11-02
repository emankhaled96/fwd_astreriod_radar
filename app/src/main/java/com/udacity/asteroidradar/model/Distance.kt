package com.udacity.asteroidradar.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Distance(
    @Json(name = "astronomical")  val astronomical : String,
    @Json(name = "lunar")  val lunar : String,
    @Json(name = "kilometers")  val kilometers : String,
    @Json(name = "miles")  val miles : String,
    ):Parcelable
