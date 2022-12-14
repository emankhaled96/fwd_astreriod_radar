package com.udacity.asteroidradar.dataSource.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.dataSource.local.dto.AsteroidsDB
import com.udacity.asteroidradar.domain.dto.Asteroid

@Dao
interface AsteroidsDao {
    @Query("select * from asteroids_table order by closeApproachDate ASC")
    fun getAsteroids(): List<AsteroidsDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: AsteroidsDB)

    @Query("DELETE from asteroids_table")
    fun deleteAll()

    @Query("SELECT * FROM asteroids_table WHERE closeApproachDate <=:date ORDER BY date(closeApproachDate) ASC ")
    fun getTodayAsteroids(date:String):List<AsteroidsDB>

}