package com.example.weatherapp.data.source.remote.model

import com.google.gson.annotations.SerializedName

class Main (
    @SerializedName("temp")
    val temp:Double,

    @SerializedName("temp_min")
    val tempMin :Double,

    @SerializedName("feels_like")
    val feelsLike :Double,

    @SerializedName("grnd_level")
    val grndLevel:Double,

    @SerializedName("humidity")
    val humidity:Int ,

    @SerializedName("pressure")
    val pressure:Double,

    @SerializedName("sea_level")
    val seaLevel :Double,

    @SerializedName("temp_max")
    val tempMax:Double,
)