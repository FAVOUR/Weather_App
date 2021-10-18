package com.example.weatherapp.di.component

import com.example.weatherapp.ui.fragment.WeatherDetailFragment
import com.example.weatherapp.di.module.*
import com.example.weatherapp.ui.activity.WeatherActivity
import com.example.weatherapp.ui.fragment.WeatherFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class, DataSourceModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(weatherActivity: WeatherActivity)
    fun inject(weatherFragment: WeatherFragment)
    fun inject(weatherDetailFragment: WeatherDetailFragment)

}