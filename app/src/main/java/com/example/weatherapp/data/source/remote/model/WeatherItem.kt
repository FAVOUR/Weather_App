package com.example.weatherapp.data.source.remote.model

import com.google.gson.annotations.SerializedName

class WeatherItem(
    @SerializedName("icon")
    val icon: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("main")
    val main: String,

    @SerializedName("id")
    val id: Int
)