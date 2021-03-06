package com.example.weatherapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentWeatherDetailBinding
import com.example.weatherapp.ui.model.WeatherData
import com.example.weatherapp.ui.util.Extensions.displayCollapsingToolBarTitle
import com.example.weatherapp.ui.util.Extensions.formatTemperature
import com.example.weatherapp.ui.util.Extensions.getAppInstance
import com.example.weatherapp.ui.util.Extensions.getImageBasedOnLandMark
import com.example.weatherapp.ui.util.Extensions.getLocation
import com.example.weatherapp.ui.util.Extensions.setWeatherConditionImage
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.example.weatherapp.ui.viewmodel.WeatherViewModelFactory
import javax.inject.Inject


class WeatherDetailFragment : Fragment() {

    private val args: WeatherDetailFragmentArgs by navArgs()

    @Inject
    lateinit var weatherViewModelFac: WeatherViewModelFactory

    private val viewModel by activityViewModels<WeatherViewModel> {
        weatherViewModelFac
    }

    lateinit var binding: FragmentWeatherDetailBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (requireActivity().application as WeatherApp).appComponent.inject(this)
        requireActivity().getAppInstance().appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)


        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val weatherData = viewModel.uiState.value?.find {
            it.city == args.city
        }



        weatherData?.let {
            setViewData(weatherData)

        } ?: view.findNavController().navigateUp()


        weatherData?.let {
            displayCollapsingToolBarTitle(
                appBarLayout = binding.appbarAbl,
                collapsingToolbarLayout = binding.toolbarLayout,
                dataWhenCollapsed = getLocation(it),
                dataWhenNotCollapsed = ""
            )
        }

    }


    private fun setViewData(weatherData: WeatherData) {

        binding.toolbarLayout.background = ContextCompat.getDrawable(
            binding.root.context,
            getImageBasedOnLandMark(weatherData.city)
        )

        binding.coordinatorLayout.background = ContextCompat.getDrawable(
            binding.root.context,
            getImageBasedOnLandMark(weatherData.city)
        )


        binding.weatherSummary.apply {

            weatherData.also {

                cityAndCountryTv.text = getLocation(it)
                weatherDescTv.text = it.description
                temperatureTv.text = formatTemperature(it.temperature)
                setWeatherConditionImage(
                    weatherIdentifier = it.weatherConditionIdentifier,
                    imageView = weatherIconIv
                )

            }
        }

        binding.mainWeatherSummary.apply {


            weatherData.also {

                tempInfoTv.text = formatTemperature(it.temperature)

            }.coreWeatherDetails.also {

                humidityInfoTv.text = it.humidity.toString()
                tempFeelsLikeInfoTv.text = formatTemperature(it.feelsLike)
                maxTempInfoTv.text = formatTemperature(it.maxTemperature)
                minTempInfoTv.text = formatTemperature(it.minTemperature)
                windInfoTv.text = it.windSpeed.toString()
                pressureInfoTv.text = it.pressure.toString()

            }

        }


    }

}