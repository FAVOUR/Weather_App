package com.example.weatherapp.data.source.local.dao

import androidx.room.*
import com.example.weatherapp.data.source.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherData(weatherDataList: WeatherEntity)

    @Update
    fun updateWeatherData(weatherDataList: List<WeatherEntity>)


    @Query("SELECT * from weatherentity ORDER by country ASC")
    fun getAllWeatherData(): Flow<List<WeatherEntity>>


    @Query("DELETE FROM weatherentity")
    suspend fun deleteAllWeatherData()


}