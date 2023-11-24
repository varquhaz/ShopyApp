package com.varqulabs.shopyapp.presentation.home.util

import com.varqulabs.shopyapp.R

fun errorDrawable(category: String): Int{
    return when(category){
        "men's clothing"  -> {
            R.drawable.clothes_suit
        }
        "jewelery" -> {
            R.drawable.jewelry_store
        }
        "electronics" -> {
            R.drawable.mouse
        }
        "women's clothing" -> {
            R.drawable.clothes_suit
        }
        else -> {
            R.drawable.ic_launcher_foreground
        }
    }
}