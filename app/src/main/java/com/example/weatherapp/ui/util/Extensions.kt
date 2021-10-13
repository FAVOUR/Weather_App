package com.example.weatherapp.ui.util

import android.Manifest.permission
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresPermission
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.weatherapp.WeatherApp
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.R

object Extensions {

    const val IMAGE_SUFFIX_PNG = ".png"
    const val TEMPERATURE_DEGREE = '\u00B0'
    const val TEMPERATURE_UNIT_METRIC= "metric"

    //Cities
    const val KENYA = "Kenya"
    const val CAIRO = "Cairo"
    const val LAGOS = "Lagos"
    const val ABUJA = "Abuja"
    const val NEW_YORK = "New York"
    const val TEXAS = "Texas"
    const val AMAZON = "Amazon"
    const val WBELARUS = "WBelarus"
    const val LESOTHO = "Lesotho"
    const val JAKATA = "Jakata"
    const val ANKARA = "Ankara"
    const val KANO = "Kano"
    const val PERU = "Peru"
    const val WINNIPEG = "Winnipeg"
    const val BAGDAD = "Bagdad"
    const val WESTHAM = "Westham"


    fun setWeatherConditionImage(
        imageView: ImageView,
        icon: String,
        @DrawableRes defaultImage: Int
    ) {
        Glide
            .with(imageView.context)
            .load( BuildConfig.IMAGE_ENDPOINT + icon+IMAGE_SUFFIX_PNG)
//          .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
//          .placeholder(defaultImage)
            .into(imageView)
    }


    fun getImageBasedOnLandMark(city: String): Int {
        return when (city) {
            KENYA -> {
                Log.e("Kemya","here")
                R.drawable.kenya
            }
            CAIRO -> {
                R.drawable.kenya
            }
            LAGOS -> {
                R.drawable.kenya
            }
            ABUJA -> {
                R.drawable.kenya
            }
            NEW_YORK -> {
                R.drawable.kenya
            }
            TEXAS -> {
                R.drawable.kenya
            }
            AMAZON -> {
                R.drawable.kenya
            }
            WBELARUS -> {
                R.drawable.kenya
            }
            LESOTHO -> {
                R.drawable.kenya
            }
            JAKATA -> {
                R.drawable.kenya
            }
            ANKARA -> {
                R.drawable.kenya
            }
            KANO -> {
                R.drawable.kenya
            }
            PERU -> {
                R.drawable.kenya
            }
            WINNIPEG -> {
                R.drawable.kenya
            }
            BAGDAD -> {
                R.drawable.kenya
            }
            WESTHAM -> {
                R.drawable.kenya
            }
            else -> {
                R.drawable.ic_unknown
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
            ?: return null
        return cm.activeNetworkInfo
    }


}