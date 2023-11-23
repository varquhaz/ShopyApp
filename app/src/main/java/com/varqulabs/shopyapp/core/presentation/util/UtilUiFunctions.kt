package com.varqulabs.shopyapp.core.presentation.util

fun String.replaceFirstChar(transform: (Char) -> CharSequence)
        : String {

    return if (isNotEmpty())
        transform(this[0]).toString() + substring(1)
    else
        this
}