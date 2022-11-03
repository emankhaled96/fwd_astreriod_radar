package com.udacity.asteroidradar.dataSource.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asteroids_table")
data class AsteroidsDB(
    @PrimaryKey
    val id: Long,
    val codename: String,
   @ColumnInfo(name = "closeApproachDate") val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)


