package com.example.weatherapp.data.source.remote.model

import com.google.gson.annotations.SerializedName

class Sys (
    @SerializedName("country")
    var country: String,

    @SerializedName("sunrise")
    var sunrise:Int,

    @SerializedName("sunset")
    var sunset:Int,

    @SerializedName("message")
    var message:Double
)