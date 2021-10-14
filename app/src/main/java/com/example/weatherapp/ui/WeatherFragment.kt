package com.example.weatherapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.model.WeatherData
import com.example.weatherapp.ui.util.Extensions.ABUJA
import com.example.weatherapp.ui.util.Extensions.AMAZON
import com.example.weatherapp.ui.util.Extensions.ANKARA
import com.example.weatherapp.ui.util.Extensions.BAGHDAD
import com.example.weatherapp.ui.util.Extensions.CAIRO
import com.example.weatherapp.ui.util.Extensions.JAKATA
import com.example.weatherapp.ui.util.Extensions.KANO
import com.example.weatherapp.ui.util.Extensions.KENYA
import com.example.weatherapp.ui.util.Extensions.LAGOS
import com.example.weatherapp.ui.util.Extensions.LESOTHO
import com.example.weatherapp.ui.util.Extensions.NEW_YORK
import com.example.weatherapp.ui.util.Extensions.PERU
import com.example.weatherapp.ui.util.Extensions.TEXAS
import com.example.weatherapp.ui.util.Extensions.BELARUS
import com.example.weatherapp.ui.util.Extensions.WESTHAM
import com.example.weatherapp.ui.util.Extensions.WINNIPEG
import com.example.weatherapp.ui.util.Extensions.getAppInstance
import com.example.weatherapp.ui.util.Extensions.isNetworkConnected
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherFragment : Fragment() {

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private val viewModel by activityViewModels<WeatherViewModel> {
        weatherViewModelFactory
    }

    val cities by lazy {
        arrayListOf(
            KENYA, CAIRO, LAGOS, ABUJA, NEW_YORK, TEXAS, AMAZON, BELARUS, LESOTHO, JAKATA,
            ANKARA, KANO, PERU, WINNIPEG, BAGHDAD, WESTHAM
        )
//        arrayListOf(KENYA,CAIRO)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (requireActivity().application as WeatherApp).appComponent.inject(this)
        requireActivity().getAppInstance().appComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeatherBinding.inflate(inflater, container, false)
//         return  binding.root


        // show the spinner when [MainViewModel.displaySpinner] is true
        viewModel.displaySpinner.observe(viewLifecycleOwner) { showSpinner ->
            binding.spinner.visibility = if (showSpinner) View.VISIBLE else View.GONE
        }

        // Show a snackbar whenever the [ViewModel.snackbarMessage] is updated a non-null value
        viewModel.snackbarMessage.observe(viewLifecycleOwner) { text ->
            text.getContentIfNotHandled()?.let {
                Snackbar.make(binding.root, text.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }

        //Setup Adapter
        val adapter = WeatherAdapter { weatherData, view ->
            navigateToWeatherDetailFragment(weatherData = weatherData, view = view)
        }
        binding.weatherListRv.adapter = adapter
        subscribeUi(adapter)
        requireActivity().updateWeather(cities)




        return binding.root


    }


    private fun subscribeUi(adapter: WeatherAdapter) {
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                viewModel.uiState.collect { weather ->
                    Log.e("viewModel.uiState", Gson().toJson(weather))
                    adapter.submitList(weather)
                }
            }
        }
    }

    private fun FragmentActivity.updateWeather(listOfCities: List<String>) {
        if (isNetworkConnected()) {
            viewModel.updateWeatherData(cities = listOfCities)
        } else {
            Toast.makeText(
                activity,
                resources.getString(R.string.no_internet_message),
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    private fun navigateToWeatherDetailFragment(weatherData: WeatherData, view: View) {
        val direction =
            WeatherFragmentDirections.actionWeatherFragmentToWeatherDetailsFragment(
                weatherData.city
            )
        view.findNavController().navigate(direction)
    }


}