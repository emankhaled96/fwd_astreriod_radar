package com.udacity.asteroidradar.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.model.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofitAsteroid =
    Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(Constants.BASE_URL)
        .build()
private val retrofitImage =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()

interface AsteroidApiService {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): String
}

interface ImageAPIService {
    @GET("planetary/apod")
    suspend fun getImageOfDay(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): PictureOfDay

}

object ApiService {
    val retrofitServiceAsteroids: AsteroidApiService by lazy {
        retrofitAsteroid.create(AsteroidApiService::class.java)
    }
    val retrofitServiceImage: ImageAPIService by lazy {
        retrofitImage.create(ImageAPIService::class.java)
    }

}