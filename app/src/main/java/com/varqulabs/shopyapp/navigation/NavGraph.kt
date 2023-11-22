package com.varqulabs.shopyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.varqulabs.shopyapp.presentation.detail.DetailScreen
import com.varqulabs.shopyapp.presentation.home.HomeScreen
import kotlinx.coroutines.flow.SharingStarted

@Composable
fun SetupNavGraph(
    navHostController: NavHostController,
    startDestination: Screen
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        homeRoute(
            onItemClick = { idProduct ->
                navHostController.navigate(Screen.Detail.passProductId(idProduct))
            }
        )
        detailRoute(
            onBack = {
                navHostController.navigate(Screen.Home.route)
            }
        )
    }

}

fun NavGraphBuilder.homeRoute(
    onItemClick: (String) -> Unit
){
    composable(route = Screen.Home.route){
        HomeScreen(
            onItemClick = onItemClick
        )
    }
}

fun NavGraphBuilder.detailRoute(
    onBack: () -> Unit
){
    composable(route = Screen.Detail.route){
        DetailScreen(
            onBack = onBack
        )
    }
}