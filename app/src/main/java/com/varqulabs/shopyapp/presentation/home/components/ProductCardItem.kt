package com.varqulabs.shopyapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.varqulabs.shopyapp.R
import com.varqulabs.shopyapp.domain.model.Product

@Composable
fun ProductCardItem(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clip(Shapes().large)
            .clickable {
                onClick()
            },
        elevation = CardDefaults.cardElevation(12.dp)

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp).background(Color.White),
        ){
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "Product Image",
                error = painterResource(id = R.drawable.ic_launcher_foreground),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .clip(Shapes().medium)
                    .align(Alignment.CenterHorizontally)
                    .weight(0.75f),
            )
            Column(
                modifier = Modifier
                    .weight(0.25f)
                    .padding(horizontal = 14.dp)
            ){
                Text(
                    text = product.name,
                    modifier = Modifier.weight(0.125f)
                )
                Text(
                    text = "${product.price}",
                    modifier = Modifier.weight(0.125f)
                )
            }

        }
    }

}