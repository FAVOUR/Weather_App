package com.example.weatherapp.di.module

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.source.local.db.WeatherDatabase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
class DataBaseModuleTest(val application: Application) {
//
//    @Singleton
//    @Binds
//    fun provideWeatherDao(weatherDatabase: WeatherDatabase) {
//        weatherDatabase.weatherDao()
//    }

    @Singleton
    @Binds
    fun provideDataBase() {
        Room.databaseBuilder(application, WeatherDatabase::class.java, BuildConfig.DATABASE_NAME)
            .build()
    }
}