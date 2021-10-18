package com.example.weatherapp.di.component

import android.app.Application
import android.content.Context
import com.example.weatherapp.WeatherDetailFragment
import com.example.weatherapp.di.module.*
import com.example.weatherapp.ui.WeatherActivity
import com.example.weatherapp.ui.WeatherFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class, DataSourceModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application):AppComponent
    }

    fun inject(weatherActivity: WeatherActivity)
    fun inject(weatherFragment: WeatherFragment)
    fun inject(weatherDetailFragment: WeatherDetailFragment)

}