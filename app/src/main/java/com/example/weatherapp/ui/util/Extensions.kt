package com.example.weatherapp.ui.util

import android.Manifest.permission
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.R
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.ui.model.WeatherData
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

object Extensions {

    private const val IMAGE_SUFFIX_PNG = ".png"
    private const val TEMPERATURE_DEGREE = '\u00B0'
    const val TEMPERATURE_UNIT_METRIC = "metric"
    const val TITLE = "Weather Report"

    //Cities
    const val KENYA = "Kenya"
    const val CAIRO = "Cairo"
    const val LAGOS = "Lagos"
    const val ABUJA = "Abuja"
    const val NEW_YORK = "New York"
    const val TEXAS = "Texas"
    const val AMAZON = "Amazon"
    const val BELARUS = "Belarus"
    const val LESOTHO = "Lesotho"
    const val JAKATA = "Jakata"
    const val ANKARA = "Ankara"
    const val KANO = "Kano"
    const val PERU = "Peru"
    const val WINNIPEG = "Winnipeg"
    const val BAGHDAD = "Baghdad"
    const val WESTHAM = "Westham"


    fun setWeatherConditionImage(
        imageView: ImageView,
        weatherIdentifier: String,
    ) {
        Glide
            .with(imageView.context)
            .load(BuildConfig.IMAGE_ENDPOINT + weatherIdentifier + IMAGE_SUFFIX_PNG)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }


    fun getImageBasedOnLandMark(city: String): Int {
        return when (city) {
            KENYA -> {

                R.drawable.kenya
            }
            CAIRO -> {
                R.drawable.cairo
            }
            LAGOS -> {
                R.drawable.lagos
            }
            ABUJA -> {
                R.drawable.abuja
            }
            NEW_YORK -> {
                R.drawable.new_york
            }
            TEXAS -> {
                R.drawable.texas
            }
            AMAZON -> {
                R.drawable.amazon
            }
            BELARUS -> {
                R.drawable.belarus
            }
            LESOTHO -> {
                R.drawable.lesotho
            }
            JAKATA -> {
                R.drawable.jakata
            }
            ANKARA -> {
                R.drawable.ankara
            }
            KANO -> {
                R.drawable.kano
            }
            PERU -> {
                R.drawable.peru
            }
            WINNIPEG -> {
                R.drawable.winnipeg
            }
            BAGHDAD -> {
                R.drawable.baghdad
            }
            WESTHAM -> {
                R.drawable.westham
            }
            else -> {
                R.drawable.ic_launcher_background
            }
        }
    }


    /**
     * Gets the instance of application
     * */
    fun FragmentActivity.getAppInstance(): WeatherApp {

        return (this.application as WeatherApp)
    }


    /**
     * If network connection is connect, return true
     *
     * @return boolean value
     */
    @RequiresPermission(permission.ACCESS_NETWORK_STATE)
    fun FragmentActivity.isNetworkConnected(): Boolean {
        val info = getActiveNetworkInfo()
        return info != null && info.isConnected
    }

    /**
     * Get activity network info instace
     *
     * @return instance of [NetworkInfo]
     */
    @RequiresPermission(permission.ACCESS_NETWORK_STATE)
    private fun FragmentActivity.getActiveNetworkInfo(): NetworkInfo? {
        val cm = getAppInstance()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }


    fun getLocation(item: WeatherData) =
        "${item.city}, ${item.country}"


    fun formatTemperature(temperature: Double) =
        "${temperature}$TEMPERATURE_DEGREE"


    fun displayCollapsingToolBarTitle(
        appBarLayout: AppBarLayout,
        collapsingToolbarLayout: CollapsingToolbarLayout,
        dataWhenCollapsed: String,
        dataWhenNotCollapsed: String
    ) {
        var isShow = true
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }

            // display data when the view is collapsed
            if (scrollRange + verticalOffset == 0) {
                collapsingToolbarLayout.title = dataWhenCollapsed
                isShow = true
            } else if (isShow) {

                // display data when the view is not collapsed
                collapsingToolbarLayout.title =
                    dataWhenNotCollapsed
                isShow = false
            }
        })
    }


    fun FragmentActivity.reportInternetConnectivityOrTakeAction(action: (() -> Unit)? = null) {

        if (isNetworkConnected()) {
            action?.invoke()
        } else {
            Toast.makeText(
                this,
                resources.getString(R.string.no_internet_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}