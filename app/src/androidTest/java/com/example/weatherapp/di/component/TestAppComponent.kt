package com.example.weatherapp.di.component

import android.app.Application
import com.example.weatherapp.di.WeatherTestApp
import com.example.weatherapp.di.module.*
import dagger.BindsInstance
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
interface TestAppComponent : AppComponent {

    @Component.Factory
    interface Factory{
        //Read about factory method
        fun create(@BindsInstance application: Application):AppComponent
    }

    fun inject(weatherTestApp: WeatherTestApp)


}