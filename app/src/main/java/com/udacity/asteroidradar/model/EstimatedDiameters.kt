package com.udacity.asteroidradar.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EstimatedDiameters(
    @Json(name="estimated_diameter_min")val min : Double,
    @Json(name="estimated_diameter_max")val max : Double,
):Parcelable
