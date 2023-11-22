package com.varqulabs.shopyapp.navigation

import com.varqulabs.shopyapp.utils.Constants.DETAIL_SCREEN_ARGUMENT_KEY

sealed class Screen(val route: String){

    object Home: Screen("home_route")

    object Detail: Screen(route = "detail_route?$DETAIL_SCREEN_ARGUMENT_KEY=" +
        "{$DETAIL_SCREEN_ARGUMENT_KEY}") {
        fun passProductId(productId: String) =
            "detail_route?$DETAIL_SCREEN_ARGUMENT_KEY=$productId"

    }

}