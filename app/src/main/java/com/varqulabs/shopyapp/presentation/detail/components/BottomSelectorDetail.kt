package com.varqulabs.shopyapp.presentation.detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.shopyapp.R
import com.varqulabs.shopyapp.core.presentation.components.RatingBar
import com.varqulabs.shopyapp.domain.model.Product
import com.varqulabs.shopyapp.presentation.detail.util.NoRippleInteractionSource
import com.varqulabs.shopyapp.presentation.detail.util.replaceFirstChar
import java.text.NumberFormat

@Composable
fun BottomSelectorDetail(
    product: Product,
    modifier: Modifier = Modifier
) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Description", "Category", "Ratings")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title, fontSize = 15.sp, fontWeight = FontWeight.Bold) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    interactionSource = NoRippleInteractionSource()
                )
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            when (tabIndex) {
                0 -> BottomDetail(detailDescription = product.description)
                1 -> BottomCategory(productCategory = product.category)
                2 -> BottomRatingScore(
                    ratingScore = product.ratingScore,
                    ratingCount = product.ratingCount
                )
            }
        }
    }
}

@Composable
fun BottomDetail(
    detailDescription: String
) {
    Text(
        text = "- ${
            detailDescription.replaceFirstChar {
                it.titlecase()
            }
        }",
        fontSize = 14.sp,
        maxLines = 5,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun BottomCategory(
    productCategory: String
) {

    val numberFormat = NumberFormat.getInstance()
    numberFormat.maximumFractionDigits = 2
    numberFormat.minimumFractionDigits = 2
    numberFormat.isGroupingUsed = true

    Text(
        text = "* ${
            productCategory.replaceFirstChar { char ->
                char.titlecase()
            }
        }",
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )

}

@Composable
fun BottomRatingScore(
    ratingScore: Double,
    ratingCount: Int
) {
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            RatingBar(
                rating = ratingScore.toFloat(),
                imageFilled = R.drawable.star,
                imageOutlined = R.drawable.star_outline
            )
            Text(
                text = "(${ratingScore})",
            )
        }
        Text(
            text = "Based on $ratingCount ratings."
        )
    }


}