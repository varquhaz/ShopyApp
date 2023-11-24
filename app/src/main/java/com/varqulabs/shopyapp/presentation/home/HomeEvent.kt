package com.varqulabs.shopyapp.presentation.home

sealed class HomeEvent {
    data object ReloadNetwork : HomeEvent()
    data class SortByFilter(val sortFilter: String) : HomeEvent()
    data class FilterByCategory(val category: String) : HomeEvent()

}