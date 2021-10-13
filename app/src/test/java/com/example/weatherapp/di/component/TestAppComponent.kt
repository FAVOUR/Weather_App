package com.example.weatherapp.di.component

import com.example.weatherapp.WeatherTestApp
import com.example.weatherapp.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DataBaseModuleTest::class, NetworkModuleTest::class,
        DataBaseModule::class, DataSourceModule::class,
        NetworkModule::class, RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface TestAppComponent:AppComponent {

    fun inject(weatherTestApp: WeatherTestApp)


}