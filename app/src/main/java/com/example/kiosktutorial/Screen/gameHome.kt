package com.example.kiosktutorial.Screen

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
fun GameSelectionButton(title: String, modifier:Modifier? = null, desc:String? = null, icon: Int? = null, func: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .heightIn(min = 100.dp)
            .padding(all = 5.dp)
            .clickable(
                onClick = func
            )
            .background(MaterialTheme.colors.background)
            .composed{modifier?:Modifier}

        ,   Alignment.TopStart
    ) {
        var paintD = painterResource(id = icon ?: R.drawable.ic_launcher_background)
        Image(
            painter = paintD,
            contentDescription = "$title 아이콘",
            contentScale = ContentScale.Fit,

            modifier = Modifier
                .heightIn(max = 100.dp)
            ,   alignment = Alignment.Center

        )

        val size =

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(red = 255, green =255, blue = 255, alpha = 100))
                    .padding(
                        all= 10.dp
                    )
                ,   horizontalAlignment = Alignment.CenterHorizontally
                , verticalArrangement = Arrangement.Center

            ) {


                Text(
                    text = title
                    , fontSize = 20.sp
                )


                desc?.let{
                    Spacer(modifier= Modifier.height(5.dp))

                    Text(
                        text = desc
                        ,   fontSize = 14.sp
                    )

                }

            }
    }
}

@Composable
fun GameHomeScreen(navHostController: NavHostController){
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
                text= "KT IT 서포터즈가 함께하는\n 키오스크 연습하기",
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

        // selection
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(all = 5.dp)
        ) {
            GameSelectionButton("숫자 순서 맞추기 ", desc = "1부터 9까지의 순서를 순대로 맞추고 역으로 누르면 되는 게임입니다.", func = {
                navHostController.navigate(Screen.KioskTutorialSelection.route)

            })
            GameSelectionButton("글자 순 맞추기", desc = "글자안의 색상을 맞추는 게임입니다.", func = {
                navHostController.navigate(Screen.KioskExerciseSelection.route)
            })


        }

    }
}


@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewGameHome(){
    val navCtrl = rememberNavController()
    GameHomeScreen(navCtrl)
}


@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewDarkGameHome(){
    val navCtrl = rememberNavController()
    GameHomeScreen(navCtrl)
}

class ColorText{
    constructor(text:String,color:Int)
    {


    }
}