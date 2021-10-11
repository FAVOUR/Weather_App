package com.example.weatherapp.data.source.remote.model

import com.google.gson.annotations.SerializedName

class Wind(
    @SerializedName("deg")
    val deg: Double,

    @SerializedName("speed")
    val speed: Double
)