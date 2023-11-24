package com.varqulabs.shopyapp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.varqulabs.shopyapp.R
import com.varqulabs.shopyapp.core.presentation.components.ErrorContent
import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.presentation.home.components.ProductCardItem
import com.varqulabs.shopyapp.presentation.home.components.WelcomeBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onItemClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state

    // Status Bar Colors
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = Color.White.copy(alpha = 0.5f)
        )
        onDispose {}
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            if(state.isLoading){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            } else {
                if (state.products.isEmpty()) {
                    systemUiController.setStatusBarColor(Color.Black)
                    Column(){
                        WelcomeBar(username = "Username")
                        ErrorContent(
                            exceptionType = "Internet Connection",
                            exceptionImage = R.drawable.no_interet,
                            reload = {
                                viewModel.onEvent(HomeEvent.ReloadNetwork)
                            }
                        )
                    }
                } else {
                    Column() {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 12.dp),
                            contentPadding = PaddingValues(0.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            item(
                                span = {
                                    GridItemSpan(2)
                                }
                            ) {
                                WelcomeBar("Juanjo")
                            }
                            items(
                                items = state.products,
                                key = { producto: Product -> producto.id }
                            ) { product ->
                                ProductCardItem(
                                    product = product,
                                    onClick = { onItemClick(product.id) }
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}