package com.udacity.asteroidradar.dataSource.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.dataSource.local.dto.AsteroidsDB
import com.udacity.asteroidradar.domain.dto.Asteroid

@Dao
interface AsteroidsDao {
    @Query("select * from asteroids_table order by closeApproachDate ASC")
    fun getAsteroids(): LiveData<List<AsteroidsDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: AsteroidsDB)
}