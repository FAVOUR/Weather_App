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
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.ui.adapter.WeatherAdapter
import com.example.weatherapp.ui.util.Extensions.KENYA
import com.example.weatherapp.ui.util.Extensions.getAppInstance
import com.example.weatherapp.ui.util.Extensions.isNetworkConnected
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class WeatherFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null


    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private val viewModel by activityViewModels<WeatherViewModel>() {
        weatherViewModelFactory
    }

     val cities by lazy {
//        arrayListOf(KENYA,CAIRO, LAGOS, ABUJA, NEW_YORK, TEXAS, AMAZON, WBELARUS)
        arrayListOf(KENYA)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (requireActivity().application as WeatherApp).appComponent.inject(this)
        requireActivity().getAppInstance().appComponent.inject(this)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeatherBinding.inflate(inflater, container, false)
//         return  binding.root


        // show the spinner when [MainViewModel.spinner] is true
        viewModel.displaySpinner.observe(viewLifecycleOwner) { showSpinner ->
            binding.spinner.visibility = if (showSpinner) View.VISIBLE else View.GONE
        }

        // Show a snackbar whenever the [ViewModel.snackbar] is updated a non-null value
        viewModel.snackbarMessage.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
            }
        }

        //Setup Adapter
        val adapter = WeatherAdapter()
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

    private fun FragmentActivity.updateWeather(listOfCities:List<String>) {
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

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment WeatherFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            WeatherFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}