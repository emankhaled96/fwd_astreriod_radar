package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.DateUtils
import com.udacity.asteroidradar.dataSource.local.AsteroidsDatabase
import com.udacity.asteroidradar.dataSource.remote.ApiService
import com.udacity.asteroidradar.dataSource.remote.AsteroidApi
import com.udacity.asteroidradar.domain.dto.Asteroid
import com.udacity.asteroidradar.domain.mapper.toDomainModel
import com.udacity.asteroidradar.domain.repository.AsteroidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _pictureOfTheDayURL = MutableLiveData<String>()
    val pictureOfDayURL: LiveData<String>
        get() = _pictureOfTheDayURL


    private val asteroidDao = AsteroidsDatabase.getInstance(application).asteroidDao
    private val asteroidApi = ApiService.retrofitService
    private val imageApi = ApiService.retrofitServiceImage

    private val asteroidsRepository = AsteroidRepository(asteroidDao, asteroidApi)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            asteroidsRepository.refreshAsteroids()
        }
        getPictureOfDay()
    }

    private val _todayAsteroids = MutableLiveData<List<Asteroid>>()
    val todayAsteroids: LiveData<List<Asteroid>> = _todayAsteroids

    private val _savedAsteroids = MutableLiveData<List<Asteroid>>()
    val savedAsteroids: LiveData<List<Asteroid>> = _savedAsteroids

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>> = _asteroids

    fun getTodayAsteroids() = viewModelScope.launch(Dispatchers.IO) {
        _todayAsteroids.postValue(
            asteroidDao.getTodayAsteroids(DateUtils().getToday()).toDomainModel()
        )
    }

    fun getAsteroids() = viewModelScope.launch(Dispatchers.IO) {
        _asteroids.postValue(asteroidDao.getAsteroids().toDomainModel())
    }


    private fun getPictureOfDay() = viewModelScope.launch {
        try {
            val imageResponse = imageApi.getImageOfDay()
            if (imageResponse.mediaType == "image") {
                _pictureOfTheDayURL.postValue(imageResponse.url)
            }
            Log.d("Image", imageResponse.toString())
        } catch (e: Exception) {
            Log.d("error", e.message.toString())

        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}