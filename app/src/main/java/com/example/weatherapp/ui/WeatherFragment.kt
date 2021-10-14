package com.example.weatherapp.ui

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
import com.example.weatherapp.ui.util.Extensions.reportInternetConnectivityOrTakeAction
import com.example.weatherapp.ui.util.Extensions.getAppInstance
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
        viewModel.displaySpinner.observe(viewLifecycleOwner) { showSpinner ->
            binding.spinner.visibility = if (showSpinner) View.VISIBLE else View.GONE
        }

        // Show a snackbar whenever the [ViewModel.snackbarMessage] is updated a non-null value
        viewModel.snackBarMessage.observe(viewLifecycleOwner) { text ->
            text.getContentIfNotHandled()?.let {
                Snackbar.make(binding.root, text.toString(), Snackbar.LENGTH_SHORT).show()
            }
        }

        //Setup Adapter
        val adapter = WeatherAdapter { weatherData, view ->
            navigateToWeatherDetailFragment(weatherData = weatherData, view = view)
        }
        binding.weatherListRv.adapter = adapter

        subscribeUi(adapter,binding.swipeRefreshSl)

        requireActivity().reportInternetConnectivityOrTakeAction()

        binding.swipeRefreshSl.setOnRefreshListener {

            requireActivity().updateWeather()


        }
        binding.swipeRefreshSl.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

//            binding.swipeRefreshSl.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    // Your code to refresh the list here.
//                    // Make sure you call swipeContainer.setRefreshing(false)
//                    // once the network request has completed successfully.
//                    fetchTimelineAsync(0);
//                }
//            });
        // Configure the refreshing colors
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//            android.R.color.holo_green_light,
//            android.R.color.holo_orange_light,
//            android.R.color.holo_red_light);
//    }


        return binding.root


    }


    private fun subscribeUi(adapter: WeatherAdapter,swipeRefreshLayout: SwipeRefreshLayout) {
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

                    //Stop swipe layout from refreshing if refreshing is ongoing
                    swipeRefreshLayout.apply {
                        if(isRefreshing){

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
        Log.e("Triggered >>>>>", " Yeah ")

        reportInternetConnectivityOrTakeAction{
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