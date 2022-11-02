package com.udacity.asteroidradar.repository

import com.udacity.asteroidradar.DateUtils
import com.udacity.asteroidradar.api.ApiService
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(
    private val asteroidsDatabase: AsteroidsDatabase
) {
    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroids = ApiService.retrofitServiceAsteroids
            asteroidsDatabase.asteroidDao.insertAll(
                parseAsteroidsJsonResult(
                    JSONObject(
                        asteroids.getAsteroids(
                            DateUtils().getToday(),
                            DateUtils().getEndDate()
                        )
                    )
                )
            )
        }
    }
}