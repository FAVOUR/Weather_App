package com.example.weatherapp.data.source.local

import com.example.weatherapp.data.source.local.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getWeatherReports(): Flow<List<WeatherEntity>>

    fun saveWeatherReports(allWeatherReports: List<WeatherEntity>)

    fun updateWeatherReports(allWeatherReports: List<WeatherEntity>)

    fun deleteAllWeatherReport()
}