package com.udacity.asteroidradar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Diameters(
    val kilometers : EstimatedDiameters,
    val meters : EstimatedDiameters,
    val miles : EstimatedDiameters,
    val feet : EstimatedDiameters
):Parcelable
