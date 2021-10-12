package com.example.weatherapp.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.source.local.dao.WeatherDao
import com.example.weatherapp.data.source.local.entity.WeatherEntity


@Database(entities = [WeatherEntity::class],version = 1)
abstract class WeatherDatabase : RoomDatabase(){

    abstract fun weatherDao () : WeatherDao

}