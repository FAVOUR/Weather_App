package com.example.weatherapp.ui.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.R

object Extensions {

    const val IMAGE_SUFFIX=".png"

    //Cities
    const val KENYA="Kenya"
    const val CAIRO="Cairo"
    const val LAGOS="Lagos"
    const val ABUJA="Abuja"
    const val NEW_YORK="New York"
    const val TEXAS="Texas"
    const val AMAZON="Amazon"
    const val WBELARUS="WBelarus"
    const val LESOTHO="Lesotho"
    const val JAKATA="Jakata"
    const val ANKARA="Ankara"
    const val KANO="Kano"
    const val PERU="Peru"
    const val WINNIPEG="Winnipeg"
    const val BAGDAD="Bagdad"
    const val WESTHAM="Westham"


    fun setWeatherConditionImage(imageView: ImageView, icon:String, @DrawableRes defaultImage:Int){
        Glide
            .with(imageView.context)
            .load(BuildConfig.BASEURL+BuildConfig.IMAGE_ENDPOINT+icon)
//          .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
//          .placeholder(defaultImage)
            .into(imageView)
    }


    fun getImageBasedOnLandMark(city:String): Int{
        return when(city){
            KENYA->{
                R.drawable.kenya
            } CAIRO->{
                R.drawable.kenya
            } LAGOS->{
                R.drawable.kenya
            } ABUJA->{
                R.drawable.kenya
            } NEW_YORK->{
                R.drawable.kenya
            } TEXAS->{
                R.drawable.kenya
            } AMAZON->{
                R.drawable.kenya
            } WBELARUS->{
                R.drawable.kenya
            } LESOTHO->{
                R.drawable.kenya
            } JAKATA->{
                R.drawable.kenya
            } ANKARA->{
                R.drawable.kenya
            } KANO->{
                R.drawable.kenya
            } PERU->{
                R.drawable.kenya
            } WINNIPEG->{
                R.drawable.kenya
            } BAGDAD->{
                R.drawable.kenya
            } WESTHAM->{
                R.drawable.kenya
            } else ->{
                R.drawable.ic_unknown
            }
        }
    }


}