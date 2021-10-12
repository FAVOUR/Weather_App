package com.example.weatherapp.data.source.remote

import com.example.weatherapp.data.source.remote.model.WeatherReport
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getCurrentDataResource(city:String): Response<WeatherReport>
}