package com.varqulabs.shopyapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.varqulabs.shopyapp.R

@Composable
fun ProductCardItem(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clip(Shapes().extraLarge)
            .clickable {
                onClick()
            }

    ){
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ){
            AsyncImage(
                model = "https://avatars.githubusercontent.com/u/135390925?s=400&u=25a16b20d00273658ebd7a917ed8d1ae1ef67ba1&v=4",
                contentDescription = "Product Image",
                error = painterResource(id = R.drawable.ic_launcher_foreground),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .clip(Shapes().extraLarge)
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                "Product name"
            )
        }
    }

}