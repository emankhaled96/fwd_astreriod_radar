package com.udacity.asteroidradar.domain.mapper

import com.udacity.asteroidradar.dataSource.local.dto.AsteroidsDB
import com.udacity.asteroidradar.dataSource.remote.dto.AsteroidsResponse
import com.udacity.asteroidradar.domain.dto.Asteroid


// Mapper to map from Database Model to Domain Model
fun List<AsteroidsDB>.toDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

// Mapper to map from network model to database model
fun ArrayList<AsteroidsResponse>.toDatabaseModel(): Array<AsteroidsDB> {
    return map {
        AsteroidsDB(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}

