package com.example.weatherapp.data.source.remote

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.source.remote.helper.ResponseFromServer
import com.example.weatherapp.data.source.remote.model.WeatherReport
import com.example.weatherapp.data.source.remote.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val weatherService: WeatherService
) : RemoteDataSource {


    override suspend fun getCurrentDataResource(city: String): Response<WeatherReport> {

//        return withContext(Dispatchers.IO) {
           return weatherService.getCurrentWeather(appId =BuildConfig.WEATHER_API_KEY, q = city)
//        }

    }


}