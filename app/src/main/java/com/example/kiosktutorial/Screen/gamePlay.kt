package com.example.kiosktutorial.Screen

import android.app.GameManager
import android.content.res.Configuration.*
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R


@Composable
fun GamePlay(name:String){
    if(name=="NumberGame") //숫자 순서 맞추기 게임
    {
        var num:Int =0;

    }
    else if(name == "ColorGame") //글자 색상 맞추기 게임
    {
        var num:Int =3; //색깔의 종류
    }

}

@Composable
fun GamePlayScreen(navHostController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xfffed55f))
        ) {
            val paintD = painterResource(R.drawable._1_kt_wordmark__standard__01)
//            Image(painter = paintD, contentDescription = "")

            Text(
                text= "아래의 글을 보고 글자 안의\n" +
                        " 색상을 알맞게 골라주세요 ",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
                fontSize = 20.sp
            )

        }

        val context = LocalContext.current
        val backgroundColor = Color(0xfffff0a3)

        var toast:Toast? = null

    }
}
@Preview
@Composable
fun PreviewGame(){
    val navCtrl = rememberNavController()
    GamePlayScreen(navHostController = navCtrl)
}
