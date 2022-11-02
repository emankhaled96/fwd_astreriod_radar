package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.model.Asteroid

interface AsteroidsDao {
    @Query("select * from asteroidDatabase")
    fun getAsteroids(): LiveData<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids : List<Asteroid>)
}