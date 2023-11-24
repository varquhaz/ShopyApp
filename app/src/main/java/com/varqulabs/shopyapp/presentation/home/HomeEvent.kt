package com.varqulabs.shopyapp.presentation.home

sealed class HomeEvent {
    object ReloadNetwork : HomeEvent()
}