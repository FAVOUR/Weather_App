package com.example.weatherapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class WeatherTestRunner :AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, WeatherTestApp::class.java.name, context)
    }


}