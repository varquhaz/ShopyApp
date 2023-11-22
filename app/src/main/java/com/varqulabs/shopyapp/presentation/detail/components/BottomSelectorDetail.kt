package com.varqulabs.shopyapp.presentation.detail.components

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.varqulabs.shopyapp.R
import com.varqulabs.shopyapp.core.presentation.components.RatingBar
import com.varqulabs.shopyapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun BottomSelectorDetail(
    product: Product,
    modifier: Modifier = Modifier
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Description", "Category", "Ratings")

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    interactionSource = NoRippleInteractionSource()
                )
            }
        }
        when (tabIndex) {
            0 -> BottomDetail(product = product)
            1 -> BottomDetail2(product = product)
            2 -> BottomDetail3(product = product)
        }
    }
}

class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}

@Composable
fun BottomDetail(
    product: Product
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ){
        Text(
           text = product.description
        )
    }
}

@Composable
fun BottomDetail2(
    product: Product
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ){
        Text(
            text = "${product.category}"
        )
        Text(
            text = "${product.price}"
        )
    }
}

@Composable
fun BottomDetail3(
    product: Product
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ){
            RatingBar(
                rating = product.ratingScore.toFloat(),
                imageFilled = R.drawable.star,
                imageOutlined = R.drawable.star_outline
            )
            Text(
                text = "(${product.ratingScore})",

            )

        }
        Text(
            text = "Based on ${product.ratingCount} ratings."
        )
    }
}