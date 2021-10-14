package com.example.weatherapp.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class WeatherData(
    val country: String,
    val city: String,
    val temperature: Double,
    val weatherConditionIdentifier: String,
    val coreWeatherDetails: CoreWeatherDetails,
    val description: String
) : Parcelable

@Parcelize
class CoreWeatherDetails(
    val feelsLike: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val pressure: Double,
    val humidity: Int,
    val windSpeed: Double
) : Parcelable
