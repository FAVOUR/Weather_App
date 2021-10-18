package com.example.weatherapp.di.module

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.source.local.dao.WeatherDao
import com.example.weatherapp.data.source.local.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
//class DataBaseModule(private val application: Application) {
class DataBaseModule {

    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }


    @Singleton
    @Provides
    fun provideDataBase(application: Application): WeatherDatabase {
        return Room.databaseBuilder(
            application,
            WeatherDatabase::class.java,
            BuildConfig.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }


}