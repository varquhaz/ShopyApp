package com.varqulabs.shopyapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.navigation.Screen
import com.varqulabs.shopyapp.presentation.home.components.ProductCardItem
import com.varqulabs.shopyapp.presentation.home.components.WelcomeBar

@Composable
fun HomeScreen(
    onItemClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state

    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false

    DisposableEffect(systemUiController, useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = Color.White.copy(alpha = 0.5f)
        )

        // setStatusBarColor() and setNavigationBarColor() also exist

        onDispose {}
    }

    Scaffold(

    ) {
        if(state.isLoading){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        } else {
            if(state.products.isEmpty()){
                if(state.errorMsg == null){
                    Text("Hubo un error inesperado")
                } else {
                    Text(
                        text = state.errorMsg
                    )
                }
            } else {
                Column(modifier = Modifier){

                        //WelcomeBar()
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 12.dp),
                            contentPadding = PaddingValues(0.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ){
                            item(
                                span = {
                                    GridItemSpan(2)
                                }
                            ){
                                WelcomeBar()
                            }
                            items(
                                items = state.products,
                                key = { producto : Product -> producto.id }
                            ){product ->
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