package com.example.weatherapp.data.source.remote.model

import com.google.gson.annotations.SerializedName

class Clouds(
    @SerializedName("all")
    val all: Int
)