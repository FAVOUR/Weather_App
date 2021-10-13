package com.example.weatherapp.data.source.local.dao

import androidx.room.*
import com.example.weatherapp.data.source.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface WeatherDao {


    @Insert
    fun insertWeatherData(weatherDataList:WeatherEntity)
//    fun insertWeatherData(weatherDataList:  List<WeatherEntity>)

    @Update
    fun updateWeatherData(weatherDataList:  List<WeatherEntity>)


    @Query("SELECT * from weatherentity")
    fun getAllWeatherData(): Flow<List<WeatherEntity>>


    @Query("DELETE FROM weatherentity")
    fun deleteAllWeatherData()


}