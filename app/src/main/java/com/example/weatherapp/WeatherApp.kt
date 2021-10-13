package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.di.component.DaggerAppComponent
import com.example.weatherapp.di.module.DataBaseModule
import okhttp3.internal.notifyAll
import okhttp3.internal.wait

open class WeatherApp : Application() {

    val appComponent by lazy {
        initialiseAppComponent()
    }

     fun initialiseAppComponent(): AppComponent {
        val builder = DaggerAppComponent.builder()
        //Use factory method instead
        return builder.dataBaseModule (DataBaseModule(this))
            .build()
    }

}