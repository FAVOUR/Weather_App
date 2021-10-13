package com.example.weatherapp.di.component

import com.example.weatherapp.WeatherDetailFragment
import com.example.weatherapp.di.module.*
import com.example.weatherapp.ui.WeatherFragment
import com.example.weatherapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class,DataSourceModule::class,NetworkModule::class,RepositoryModule::class,ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity:MainActivity)
    fun inject(weatherFragment: WeatherFragment)
    fun inject(weatherDetailFragment: WeatherDetailFragment)

}