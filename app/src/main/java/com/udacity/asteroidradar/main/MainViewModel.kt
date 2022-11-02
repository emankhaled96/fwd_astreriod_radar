package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.DateUtils
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.api.ApiService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _pictureOfTheDayURL = MutableLiveData<String>()
    val pictureOfDayURL: LiveData<String>
        get() = _pictureOfTheDayURL

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    init {
        getPictureOfDay()
        getAsteroids()
    }



    private fun getPictureOfDay() = viewModelScope.launch {
        try {
            val imageResponse = ApiService.retrofitService.getImageOfDay()
            if (imageResponse.mediaType == "image") {
                _pictureOfTheDayURL.postValue(imageResponse.url)
            }
            Log.d("Image", imageResponse.toString())
        } catch (e: Exception) {
            Log.d("error", e.message.toString())

        }
    }

    private fun getAsteroids() = viewModelScope.launch {
        try {
            val asteroidResponse = ApiService.retrofitService.getAsteroids(
                startDate = DateUtils().getToday(),
                endDate = DateUtils().getEndDate()
            )
            Log.d("asteroidsResponse", asteroidResponse.toString())
            _asteroids.postValue(asteroidResponse.nearAsteroid.values.flatten())


        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }
    }
}