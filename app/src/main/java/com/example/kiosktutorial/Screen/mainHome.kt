package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.*
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
fun MainSelectionButton(title: String, modifier:Modifier? = null, desc:String? = null, icon: Int? = null, func: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .heightIn(min = 100.dp)
            .padding(all = 5.dp)
            .clickable(
                onClick = func
            )
            .shadow(elevation = 5.dp)
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
                .padding(all = 5.dp)
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
fun Home(navHostController: NavHostController) {
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

            MainSelectionButton("키오스크 튜토리얼", desc = "hello", func = {
                navHostController.navigate(Screen.KioskTutorialSelection.route)
//                toast?.cancel()
//                toast = Toast.makeText(context, "키오스크 튜토리얼", Toast.LENGTH_SHORT)
//                toast?.show()
            })
            MainSelectionButton("키오스크 실전연습", func = {
                navHostController.navigate(Screen.KioskExerciseSelection.route)
//                toast?.cancel()
//                toast = Toast.makeText(context, "키오스크 실전연습", Toast.LENGTH_SHORT)
//                toast?.show()

            })
            MainSelectionButton("두뇌 활성화 게임", func = {
                toast?.cancel()
                toast = Toast.makeText(context, "두뇌 활성화 게임", Toast.LENGTH_SHORT)
                toast?.show()
            })


        }

    }

}

@Preview
@Composable
fun HomePreview(){
    val navHostController = rememberNavController()
    Home(navHostController)
}