package com.example.weatherapp.data.source.remote

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.source.remote.model.WeatherReport
import com.example.weatherapp.data.source.remote.service.WeatherService
import com.example.weatherapp.ui.util.Extensions.TEMPERATURE_UNIT_METRIC
import retrofit2.Response
import javax.inject.Inject

class DefaultRemoteDataSource @Inject constructor(
    private val weatherService: WeatherService
) : RemoteDataSource {


    override suspend fun getWeatherData(city: String): Response<WeatherReport> {

        return weatherService.getCurrentWeather(
            appId = BuildConfig.WEATHER_API_KEY,
            q = city,
            units = TEMPERATURE_UNIT_METRIC
        )

    }


}