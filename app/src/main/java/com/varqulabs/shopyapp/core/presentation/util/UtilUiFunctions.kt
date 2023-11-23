package com.varqulabs.shopyapp.core.presentation.util

import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import com.varqulabs.shopyapp.R

fun String.replaceFirstChar(transform: (Char) -> CharSequence)
        : String {

    return if (isNotEmpty())
        transform(this[0]).toString() + substring(1)
    else
        this
}

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

fun Context.shareLink(url: String){
    val sendIntent = Intent(
        Intent.ACTION_SEND
    ).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(
        sendIntent, null
    )
    startActivity(shareIntent)
}