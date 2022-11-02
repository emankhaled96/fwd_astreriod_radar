package com.udacity.asteroidradar.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AsteroidsResponse(
    val links : Links,
    @Json(name = "element_count")  val elementCount:Int,
    @Json(name = "near_earth_objects") val nearAsteroid: Map<String, List<Asteroid>>
):Parcelable
