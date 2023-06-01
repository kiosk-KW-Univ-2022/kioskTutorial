package com.example.kiosktutorial.Screen.Cafe

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.Screen

@Composable
fun CafeMain.StartView(){
    val context = LocalContext.current
    var toast: Toast? = null
    var paintD = painterResource(id = R.drawable.cafeimg)
    Column(modifier = Modifier
        .fillMaxWidth()
        .paint(painterResource(id = R.drawable.cafeimg), contentScale = ContentScale.Crop)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                "KT 카페",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "아무곳이나 터치하여 \n 진행해 주세요",
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Composable
fun CafeMain.Menu(){
    
}