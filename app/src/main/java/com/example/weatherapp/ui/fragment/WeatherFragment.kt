package com.example.weatherapp.ui.fragment

import android.R
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.model.WeatherData
import com.example.weatherapp.ui.util.Extensions.TITLE
import com.example.weatherapp.ui.util.Extensions.getAppInstance
import com.example.weatherapp.ui.util.Extensions.reportInternetConnectivityOrTakeAction
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


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().getAppInstance().appComponent.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeatherBinding.inflate(inflater, container, false)


        // show the spinner when [MainViewModel.displaySpinner] is true
        observeSpinnerData(binding)

        // Show a snackBar whenever the [ViewModel.snackBarMessage] is updated a non-null value
        observeSnackBarMessage(binding)

        //Setup Adapter
        setupAdapter(binding)

        //Check for internet connectivity
        requireActivity().reportInternetConnectivityOrTakeAction()

        //Setup swipe refresh listener
        setupSwipeRefreshListener(binding)

        binding.toolbarCtl.title = TITLE

        return binding.root


    }

    private fun observeSpinnerData(binding: FragmentWeatherBinding) {
        viewModel.displaySpinner.observe(viewLifecycleOwner) { showSpinner ->

            Log.e("showSpinner",showSpinner.toString())
            binding.spinner.visibility = if (showSpinner) View.VISIBLE else View.GONE
        }
    }

    private fun observeSnackBarMessage(binding: FragmentWeatherBinding) {
        viewModel.snackBarMessage.observe(viewLifecycleOwner) { text ->
            text.getContentIfNotHandled()?.let {
                Snackbar.make(binding.root, text.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupAdapter(binding: FragmentWeatherBinding) {
        val adapter = WeatherAdapter { weatherData, view ->
            navigateToWeatherDetailFragment(weatherData = weatherData, view = view)
        }
        binding.weatherListRv.adapter = adapter

        subscribeUi(adapter, binding.swipeRefreshSrl)
    }

    private fun setupSwipeRefreshListener(binding: FragmentWeatherBinding) {
        binding.swipeRefreshSrl.setOnRefreshListener {
            requireActivity().updateWeather()
        }
        binding.swipeRefreshSrl.setColorSchemeResources(
            R.color.holo_blue_bright,
            R.color.holo_green_light,
            R.color.holo_orange_light,
            R.color.holo_red_light
        )
    }


    private fun subscribeUi(adapter: WeatherAdapter, swipeRefreshLayout: SwipeRefreshLayout) {
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED
                viewModel.uiState.collect { weather ->
//                    Log.e("viewModel.uiState", Gson().toJson(weather))

                    //Stop swipe layout from refreshing if refreshing is ongoing
                    swipeRefreshLayout.apply {
                        if (isRefreshing) {

                            isRefreshing = false
                        }
                    }

                    //Set Adapter data
                    adapter.submitList(weather)
                }
            }
        }
    }

    private fun FragmentActivity.updateWeather() {
//        Log.e("Triggered >>>>>", " Yeah ")
        reportInternetConnectivityOrTakeAction {
            viewModel.updateWeatherData()
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