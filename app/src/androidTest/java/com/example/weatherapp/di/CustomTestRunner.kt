//package com.example.weatherapp.di
//
//import android.app.Application
//import android.content.Context
//import androidx.test.runner.AndroidJUnitRunner
//
//
///**
// * A custom [AndroidJUnitRunner] used to replace the application used in tests with a
// * [WeatherTestApp].
// */
//class CustomTestRunner :AndroidJUnitRunner() {
//
//    override fun newApplication(
//        cl: ClassLoader?,
//        className: String?,
//        context: Context?
//    ): Application {
//        return super.newApplication(cl, WeatherTestApp::class.java.name, context)
//    }
//
//
//}