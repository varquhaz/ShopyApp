package com.varqulabs.shopyapp.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.shopyapp.R

@Composable
fun WelcomeBar(

) {
    Box(
       modifier = Modifier.fillMaxWidth(),

    ){
        Image(
            painter = painterResource(id = R.drawable.demo2),
            contentDescription = "Background",
        )

        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 90.dp)
        ){
            Text(
              text = "Hi JuanJo,",
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
            )
            Text(
                text = "Shop Black Friday deals now",
                fontWeight = FontWeight.Medium,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }

}