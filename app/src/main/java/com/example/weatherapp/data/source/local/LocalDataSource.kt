package com.example.weatherapp.data.source.local

import com.example.weatherapp.data.source.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getWeatherReports(): Flow<List<WeatherEntity>>

    suspend fun saveWeatherReports(allWeatherReports: WeatherEntity)


    suspend fun deleteAllWeatherReport()
}