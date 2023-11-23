package com.varqulabs.shopyapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(

) : ViewModel(){

    //DelayLoading
    var splashLoading by mutableStateOf(true)
        private set

    fun checkLoading(){
        viewModelScope.launch {
            delay(800)
            splashLoading = false
        }
    }
}