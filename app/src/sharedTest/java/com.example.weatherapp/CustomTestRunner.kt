package com.example.weatherapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.weatherapp.di.WeatherTestApp


/**
 * A custom [AndroidJUnitRunner] used to replace the application used in tests with a
 * [WeatherTestApp].
 */
class CustomTestRunner :AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, WeatherTestApp::class.java.name, context)
    }


}