package com.example.weatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.R
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private val viewModel by viewModels<WeatherViewModel>() {
        weatherViewModelFactory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        (this.application as WeatherApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}