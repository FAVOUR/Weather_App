package com.example.weatherapp.di.component

import com.example.weatherapp.di.module.*
import com.example.weatherapp.ui.HomeFragment
import com.example.weatherapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class,DataSourceModule::class,NetworkModule::class,RepositoryModule::class,ViewModelModule::class])
interface AppComponent {

    fun inject(homeFragment:MainActivity)
    fun inject(homeFragment: HomeFragment)

}