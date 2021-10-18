package com.example.weatherapp.di.module

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.source.local.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
//class DataBaseModuleTest(val application: Application) {
class DataBaseModuleTest() {


    @Singleton
    @Provides
    @Named("test_database")
    fun provideDataBase(application:Application): WeatherDatabase {
        return Room.databaseBuilder(
            application,
            WeatherDatabase::class.java,
            BuildConfig.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()
    }
}