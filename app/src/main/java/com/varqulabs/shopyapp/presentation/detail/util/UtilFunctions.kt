package com.varqulabs.shopyapp.presentation.detail.util

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

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

fun String.replaceFirstChar(transform: (Char) -> CharSequence)
        : String {

    return if (isNotEmpty())
        transform(this[0]).toString() + substring(1)
    else
        this
}

// fun Hide onClick Ripple Effect :
class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}