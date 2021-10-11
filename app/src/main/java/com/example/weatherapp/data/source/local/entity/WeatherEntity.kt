package com.example.weatherapp.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long=0,
    val temp: Double,
    val humidity: Int,
    val weatherId: Int,
    val weatherDescription: String,
    val weatherIcon: String,
    val windDeg: Double,
    val country: String,
    val city: String,
    val windSpeed: Double
    //   , val description: String,
//    val main: String,
//    val storeTimestamp: Long
)