package com.udacity.asteroidradar.dataSource.remote

import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.domain.dto.PictureOfDay
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidApi {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): String

    @GET("planetary/apod")
    suspend fun getImageOfDay(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): PictureOfDay

}