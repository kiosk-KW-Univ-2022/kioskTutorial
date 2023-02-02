package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun MainSelectionButton(title: String, modifier:Modifier? = null, desc:String? = null, icon: Int? = null, func: () -> Unit, paint: Int) {
    var paintD = painterResource(id = paint)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .heightIn(min = 100.dp)
            .padding(all = 15.dp)
            .clickable(
                onClick = func
            )
            .background(Color.White)
            .composed { modifier ?: Modifier }

        ,   Alignment.TopStart
    ) {
        Box(modifier= Modifier
            .clip(CircleShape)
            .height(130.dp)
            .width(130.dp)
            .background(backGround)
            .padding(15.dp)
        ){
            Image(
                painter = paintD,
                contentDescription = "$title 아이콘",
                contentScale = ContentScale.Fit,

                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
                ,   alignment = Alignment.Center
            )
        }


        val size =

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 50.dp)
                ,   horizontalAlignment = Alignment.End
                , verticalArrangement = Arrangement.Center

            ) {


                Text(
                    text = title
                    , fontSize = 25.sp, fontWeight = FontWeight.Bold

                )


                desc?.let{
                    Spacer(modifier= Modifier.height(5.dp))

                    /*Text(
                        text = desc
                    ,   fontSize = 14.sp
                    )*/

                }

            }

    }
}

@Composable
fun Home(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(backGround)
                .padding(30.dp)
        ) {
            Column() {
                val paintD = painterResource(R.drawable._1_kt_wordmark__standard__01)
                Row(){
                    Image(
                        painter = paintD,
                        contentDescription = "아이콘",
                        contentScale = ContentScale.Fit,

                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)

                    )
                    Box(modifier = Modifier.height(70.dp)
                    ){
                        Text(
                            text= " 키오스크 교육용 체험 앱",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    }

                }
                Text(
                   text= "아래의 메뉴에서 하고 싶은\n체험을 골라주세요.",
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .fillMaxSize(),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

            }


        }

        val context = LocalContext.current


        var toast:Toast? = null

        // selection
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backGround)
        ) {
            Box(modifier = Modifier
                .background(
                    shape = RoundedCornerShape(
                        topStart = CornerSize(25.dp),
                        topEnd = CornerSize(25.dp),
                        bottomEnd = CornerSize(0),
                        bottomStart = CornerSize(0)
                    ), color = Color.White
                )
                .padding(10.dp)
                .fillMaxSize()
            ){
                Column {
                    MainSelectionButton("사용 지침서", func = {
                        navHostController.navigate(Screen.KioskTutorialSelection.route)

//                toast?.cancel()

//                toast = Toast.makeText(context, "키오스크 설명서 진행하기", Toast.LENGTH_SHORT)

//                toast?.show()
                    },paint=R.drawable.book)
                    dSpacer()
                    MainSelectionButton("실전연습  ", func = {
                        navHostController.navigate(Screen.KioskExerciseSelection.route)
//                toast?.cancel()
//                toast = Toast.makeText(context, "실전연습", Toast.LENGTH_SHORT)
//                toast?.show()

                    },paint= R.drawable.note)
                    dSpacer()
                    MainSelectionButton("뇌활력 게임", func = {
                        navHostController.navigate(Screen.GameHome.route)
                    },paint= R.drawable.game)
                }
            }

        }

    }

}

@Preview
@Composable
fun HomePreview(){
    val navHostController = rememberNavController()
    Home(navHostController)
}