package com.udacity.asteroidradar.dataSource.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object ApiService {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val retrofitImage =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(Constants.BASE_URL)
        .build()
    private val retrofitAsteroid =
        Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(Constants.BASE_URL)
            .build()
    val retrofitService: AsteroidApi by lazy {
        retrofitAsteroid.create(AsteroidApi::class.java)
    }
        val retrofitServiceImage: AsteroidApi by lazy {
        retrofitImage.create(AsteroidApi::class.java)
    }
}