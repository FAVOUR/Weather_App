package com.example.weatherapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.di.scope.ViewModelKey
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
 abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory:WeatherViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    internal abstract fun bindsWeatherViewModel(viewModel:WeatherViewModel): ViewModel


}