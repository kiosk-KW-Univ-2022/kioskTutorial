package com.example.kiosktutorial.Screen


import android.content.res.Configuration.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
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
import com.example.kiosktutorial.Screen.Kiosk.dSpacer
import com.example.kiosktutorial.ui.theme.backGround


@Composable
fun HamburgerMainScreen(navHostController: NavHostController){
    var paintD = painterResource(id = R.drawable.hamburger)
    Column(modifier = Modifier
        .fillMaxWidth()

        .clickable {
            navHostController.navigate(Screen.CafeKiosk.route)
        }
        .paint(paintD, contentScale = ContentScale.Crop)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(15.dp)
        ){
            Spacer(modifier = Modifier.height(50.dp))
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .width(300.dp)
                    .align(alignment = Alignment.Center)
                    .background(
                        shape = RoundedCornerShape(
                            topStart = CornerSize(25.dp),
                            topEnd = CornerSize(25.dp),
                            bottomEnd = CornerSize(25.dp),
                            bottomStart = CornerSize(25.dp),
                        ), color = backGround
                    )
            ){
                Spacer(modifier = Modifier.height(3.dp))
                Text(modifier = Modifier.fillMaxSize(),text="KT 패스트푸드", textAlign = TextAlign.Center, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
        }
        Box(modifier = Modifier
            .fillMaxSize()
        )
        {

        }

    }

}




@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewMainHamburger(){
    val navCtrl = rememberNavController()
    HamburgerMainScreen(navHostController = navCtrl)
}
