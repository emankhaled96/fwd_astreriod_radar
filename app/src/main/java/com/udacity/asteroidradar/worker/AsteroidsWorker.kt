package com.udacity.asteroidradar.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.dataSource.local.AsteroidsDatabase
import com.udacity.asteroidradar.dataSource.remote.ApiService
import com.udacity.asteroidradar.domain.repository.AsteroidRepository
import retrofit2.HttpException

class AsteroidsWorker(context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {
    companion object{
        const val WORK_NAME = "refreshAsteroids"
    }
    override suspend fun doWork(): Result {
        val database = AsteroidsDatabase.getInstance(applicationContext)
         val asteroidApi = ApiService.retrofitService

        val repository = AsteroidRepository(database.asteroidDao, asteroidApi)
        return try {
            repository.refreshAsteroids()
            Result.success()
        }catch (e:HttpException){
            Result.retry()
        }
    }
}