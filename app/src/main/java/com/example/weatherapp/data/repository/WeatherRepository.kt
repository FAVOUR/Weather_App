package com.example.weatherapp.data.repository

import com.example.weatherapp.data.source.local.entity.WeatherEntity
import com.example.weatherapp.data.source.remote.model.WeatherReport
import com.example.weatherapp.ui.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

      fun getCurrentWeather(listOfCities:List<String>): Flow<WeatherReport>
    fun clearWeatherData()
    fun insertWeatherData(allWeatherResult: WeatherEntity)

}