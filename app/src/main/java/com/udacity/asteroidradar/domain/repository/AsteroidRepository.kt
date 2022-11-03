package com.udacity.asteroidradar.domain.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.DateUtils
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.dataSource.local.AsteroidsDao
import com.udacity.asteroidradar.dataSource.local.AsteroidsDatabase
import com.udacity.asteroidradar.dataSource.remote.ApiService
import com.udacity.asteroidradar.dataSource.remote.AsteroidApi
import com.udacity.asteroidradar.domain.dto.Asteroid
import com.udacity.asteroidradar.domain.mapper.toDatabaseModel
import com.udacity.asteroidradar.domain.mapper.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(
    private val asteroidsDao: AsteroidsDao,
    private val asteroidApi : AsteroidApi
) {
    val asteroids: LiveData<List<Asteroid>> = Transformations.map(asteroidsDao.getAsteroids()) {
        it.toDomainModel()
    }


    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try{
                val asteroids = asteroidApi.getAsteroids(
                    DateUtils().getToday(),
                    DateUtils().getEndDate()
                )
                asteroidsDao.insertAll(
                    *parseAsteroidsJsonResult(
                        JSONObject(asteroids)
                    ).toDatabaseModel()
                )
            }catch (e:Exception){
                Log.d("error" , e.message.toString())
            }

        }
    }
}