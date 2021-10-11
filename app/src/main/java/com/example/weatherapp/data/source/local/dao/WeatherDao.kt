package com.example.weatherapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.data.source.local.entity.WeatherEntity


@Dao
interface WeatherDao {


    @Insert
    fun insertWeatherData(weatherDataList:  List<WeatherEntity>)

    @Update
    fun updateWeatherData(weatherDataList:  List<WeatherEntity>)


    @Query("SELECT * from weatherentity")
    fun getAllWeatherData():List<WeatherEntity>
}