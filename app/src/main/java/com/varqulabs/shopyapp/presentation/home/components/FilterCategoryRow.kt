package com.varqulabs.shopyapp.presentation.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCategoryRow(
    categorySelected: (String) -> Unit
) {
    val categories = listOf("All", "Jewelry", "Electronics")

    var selectedCategory by remember {
        mutableStateOf(categories[0])
    }

    Row(
        modifier = Modifier
    ){
        categories.forEach { category ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 4.dp),
                selected = (category == selectedCategory),
                onClick = {
                    selectedCategory = category
                    categorySelected(selectedCategory)
                },
                label = {
                    Text(text = category, fontSize = 11.sp)
                },
                shape = Shapes().large,
            )
        }
    }
}