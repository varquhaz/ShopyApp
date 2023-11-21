package com.varqulabs.shopyapp.navigation

sealed class Screen(val route: String){

    object Home: Screen("home_route")

    object Detail: Screen("detail_route")

}