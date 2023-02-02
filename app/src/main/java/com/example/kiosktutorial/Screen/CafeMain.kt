package com.example.kiosktutorial.Screen


import android.content.res.Configuration.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R



@Composable
fun CafeMainScreen(navHostController: NavHostController){
    var paintD = painterResource(id = R.drawable.cafeimg)
    Column(modifier = Modifier
        .fillMaxWidth()

        .clickable {
            navHostController.navigate(Screen.CafeKiosk.route)
        }
        .paint(painterResource(id = R.drawable.cafeimg), contentScale = ContentScale.Crop)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
        ){
            Text("KT 카페",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold)
        }
        Box(modifier = Modifier
            .fillMaxSize()
        )
        {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "아무곳이나 터치하여 진행해 주세요",
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

    }

}




@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewMainCafe(){
    val navCtrl = rememberNavController()
    CafeMainScreen(navCtrl)
}
