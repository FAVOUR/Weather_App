package com.example.weatherapp.ui.model

data class WeatherData(
    val country: String,
    val city: String,
    val temperature: Double,
    val icon: String,
    val description: String
)
