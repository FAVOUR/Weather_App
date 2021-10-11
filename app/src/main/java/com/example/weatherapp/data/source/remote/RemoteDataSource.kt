package com.example.weatherapp.data.source.remote

import com.example.weatherapp.data.source.remote.model.WeatherReport

interface RemoteDataSource {

    suspend fun getCurrentDataResource(city:String): WeatherReport
}