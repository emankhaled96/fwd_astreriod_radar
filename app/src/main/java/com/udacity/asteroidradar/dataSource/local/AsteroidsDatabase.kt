package com.udacity.asteroidradar.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.dataSource.local.dto.AsteroidsDB

@Database(entities = [AsteroidsDB::class], version = 1)
abstract class AsteroidsDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidsDao

    companion object {
        @Volatile
        private var INSTANCE: AsteroidsDatabase? = null

        fun getInstance(context: Context): AsteroidsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidsDatabase::class.java,
                        "asteroidDatabase"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
