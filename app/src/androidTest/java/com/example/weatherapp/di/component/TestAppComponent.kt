package com.example.weatherapp.di.component

import com.example.weatherapp.WeatherDetailFragment
import com.example.weatherapp.WeatherTestApp
import com.example.weatherapp.di.module.*
import com.example.weatherapp.ui.MainActivity
import com.example.weatherapp.ui.WeatherFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModuleTest::class,NetworkModuleTest::class,DataBaseModule::class, DataSourceModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface TestAppComponent {

fun inject(weatherTestApp: WeatherTestApp)


}