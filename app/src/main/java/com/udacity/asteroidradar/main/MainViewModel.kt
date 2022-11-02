package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.DateUtils
import com.udacity.asteroidradar.api.ApiService
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject

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
            val imageResponse = ApiService.retrofitServiceImage.getImageOfDay()
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
            val asteroidResponse = ApiService.retrofitServiceAsteroids.getAsteroids(
                startDate = DateUtils().getToday(),
                endDate = DateUtils().getEndDate()
            )
            Log.d("asteroidsResponse", asteroidResponse)
            _asteroids.postValue(parseAsteroidsJsonResult(JSONObject(asteroidResponse)))


        } catch (e: Exception) {
            Log.d("error", e.message.toString())
        }
    }
}