package com.example.weatherapp.di

import com.example.weatherapp.WeatherApp
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.di.component.DaggerTestAppComponent
import com.example.weatherapp.di.module.DataBaseModule

class WeatherTestApp: WeatherApp() {


    override fun initialiseAppComponent():AppComponent{
        val builder = DaggerTestAppComponent.factory()
        //Use factory method instead
        return builder.create(this)


    }

}