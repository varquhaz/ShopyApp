package com.varqulabs.shopyapp.presentation.detail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.varqulabs.shopyapp.R
import com.varqulabs.shopyapp.core.presentation.util.shareLink
import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.presentation.detail.components.BottomSelectorDetail
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    val numberFormat = NumberFormat.getInstance()
    numberFormat.maximumFractionDigits = 2
    numberFormat.minimumFractionDigits = 2
    numberFormat.isGroupingUsed = true

    // Change StatusBar Colors
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )
        onDispose {}
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    state.product?.let {
                        Text(
                            text = it.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        if (state.product != null) {
                            context.shareLink(
                                "${state.product!!.name} \n ${state.product!!.imageUrl}"
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            modifier = Modifier.size(20.dp),
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (state.product != null) {
                        context.shareLink(
                            "Estoy interesado en: \n ${state.product!!.name} \n ${state.product!!.imageUrl}"
                        )
                    }
                },
                shape = Shapes().large,
                containerColor = MaterialTheme.colorScheme.primary
            ) {

                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Shop"
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                if (state.product?.imageUrl == null) {
                    Button(onClick = {
                        viewModel.getProductDetail()
                    }) {
                        Text("Reload")
                    }
                }
                AsyncImage(
                    model = state.product?.imageUrl,
                    contentDescription = "Product Image",
                    error = painterResource(id = R.drawable.ic_launcher_foreground),
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(360.dp)
                        .padding(vertical = 40.dp, horizontal = 24.dp)
                )
            }
            BottomSelectorDetail(
                product = state.product ?: Product(
                    id = "1",
                    name = "Jacket",
                    description = "A simple Jacket",
                    price = 24.99,
                    category = "Clothes",
                    ratingScore = 4.5,
                    ratingCount = 12,
                    imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                ),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .width(240.dp)
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(12.dp))
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (state.product == null) {
                            "Price Null"
                        } else {
                            "Price: $${numberFormat.format(state.product!!.price)}"

                        }
                    )
                }
            }

        }
    }

}