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
    startDestination: Screen = Screen.Home
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onItemClick = { idProduct ->
                    navHostController.navigate(Screen.Detail.passProductId(idProduct))
                }
            )
        }
        composable(route = Screen.Detail.route) {
            DetailScreen(
                onBack = {
                    navHostController.navigate(Screen.Home.route)
                }
            )
        }
    }
}
