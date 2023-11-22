package com.varqulabs.shopyapp.presentation.home.components


import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.varqulabs.shopyapp.R
import com.varqulabs.shopyapp.core.presentation.components.RatingBar
import com.varqulabs.shopyapp.domain.model.Product

@Composable
fun ProductCardItem(
    product: Product,
    onClick: (String) -> Unit
) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clickable {
                    onClick(product.id)
                }
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
        ){
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "Product Image",
                error = painterResource(id = R.drawable.ic_launcher_foreground),
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(110.dp)
                    .align(Alignment.CenterHorizontally)

            )
            Column(
                modifier = Modifier
            ){
                Text(
                    text = product.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Row(){
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        text = "${product.description}",
                        fontSize = 11.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = "$${product.price}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(vertical = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,

                )
                RatingBar(
                    rating = product.ratingScore.toFloat(),
                    modifier = Modifier
                )
            }
        }
}



@Preview
@Composable
fun ProductPreview(

) {
    ProductCardItem(product = Product(
        id = "1",
        name = "Jacket",
        description = "A simple Jacket",
        price = 24.99,
        category = "Clothes",
        ratingScore = 4.5,
        ratingCount = 12,
        imageUrl = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
    ), onClick = {

    }

    )
}