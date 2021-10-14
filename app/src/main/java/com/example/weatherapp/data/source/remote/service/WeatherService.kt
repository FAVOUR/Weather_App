package com.example.weatherapp.data.source.remote.service

import com.example.weatherapp.data.source.remote.model.WeatherReport
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    /**
     * Get current weather of city
     *
     * @param q     String name of city
     * @param appId String api key
     * @return instance of [WeatherReport]
     */

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("q") q: String?,
        @Query("units") units: String?,
        @Query("appid") appId: String?
    ): Response<WeatherReport>

}