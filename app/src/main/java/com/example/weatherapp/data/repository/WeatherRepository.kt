package com.example.weatherapp.data.repository

import com.example.weatherapp.data.source.local.entity.WeatherEntity
import com.example.weatherapp.data.source.remote.helper.ResponseFromServer
import com.example.weatherapp.data.source.remote.model.WeatherReport
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getCurrentWeather(): Flow<List<WeatherEntity>>

    fun fetchWeatherData(listOfCities: List<String>): Flow<ResponseFromServer<WeatherReport>>

    fun clearWeatherData()

    fun insertWeatherData(allWeatherResult: WeatherEntity)

}