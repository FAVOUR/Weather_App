package com.example.weatherapp.data.source.remote

import com.example.weatherapp.data.source.remote.model.WeatherReport
import com.example.weatherapp.data.source.remote.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultRemoteDataSource(private val weatherService: WeatherService):RemoteDataSource {


    override  suspend  fun getCurrentDataResource(city:String): WeatherReport {

      return  withContext(Dispatchers.IO) {
            weatherService.getCurrentWeather(appId = "", q = city)
        }

    }


}