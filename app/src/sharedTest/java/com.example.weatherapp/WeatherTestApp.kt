package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.di.component.TestAppComponent
import com.example.weatherapp.di.module.DataBaseModule

class WeatherTestApp:WeatherApp() {


//    override fun initialiseAppComponent():AppComponent{
//
//        val builder = Dagg.builder()
//        //Use factory method instead
//        return builder.dataBaseModule (DataBaseModule(this))
//                   .build()
//
//    }

}