package com.example.weatherapp.data.source.remote.model

import com.google.gson.annotations.SerializedName

class Coord(
    @SerializedName("lon")
    val lon: Double,

    @SerializedName("lat")
    val lat: Double
)