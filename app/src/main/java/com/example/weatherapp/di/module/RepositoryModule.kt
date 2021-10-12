package com.example.weatherapp.di.module

import com.example.weatherapp.data.repository.DefaultWeatherRepository
import com.example.weatherapp.data.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindWeatherRepository(defaultWeatherRepository: DefaultWeatherRepository):WeatherRepository
}