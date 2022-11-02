package com.udacity.asteroidradar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Links(
    val next : String,
    val previous : String,
    val self : String
):Parcelable
