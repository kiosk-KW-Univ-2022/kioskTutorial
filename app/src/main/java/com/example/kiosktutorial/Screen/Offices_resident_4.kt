package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.ui.theme.DarkBackground
import com.example.kiosktutorial.ui.theme.Typography
import com.example.kiosktutorial.ui.theme.KioskTutorialTheme
import com.example.kiosktutorial.ui.theme.backGround

@Composable
fun officehome4(navHostController: NavHostController) {

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
                .height(100.dp)
                .background(backGround)
                .padding(30.dp)
        ) {
            val paintD = painterResource(R.drawable._1_kt_wordmark__standard__01)
            Spacer(modifier = Modifier.width(50.dp))
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
                        text= " 무인민원발급기",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,

                        )
                    /*Button(onClick = { *//* 종료버튼 클릭 이벤트 *//* }) {
                        Text("종료")
                    }*/
                }

            }


        }

        val context = LocalContext.current


        var toast: Toast? = null

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
                OfficeSelection4(navHostController)
            }
        }
    }
}

@Composable
fun OfficeSelection4(navHostController: NavHostController){
    Column() {
        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier
            .background(
                shape = RoundedCornerShape(
                    topStart = CornerSize(25.dp),
                    topEnd = CornerSize(25.dp),
                    bottomEnd = CornerSize(25.dp),
                    bottomStart = CornerSize(25.dp)
                ), color = backGround
            )
            .height(250.dp)
            .width(380.dp)
            .padding(30.dp)){
            Text(modifier = Modifier.align(Alignment.TopCenter),
                text = "*수수료 면제 신청 여부를 선택하여 주십시오.", fontSize = 20.sp)
            Text(modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold,
                color= Color.Blue,
                text = "\n국민기초생활수급자, 국가보훈대상자, 한부모가족지원대상자는 수수료 면제 대상자 입니다.", fontSize = 20.sp)


        }
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Spacer(modifier = Modifier.width(5.dp))
            Button(modifier = Modifier
                .width(130.dp)
                .height(80.dp)
                .shadow(20.dp, shape = RectangleShape, clip = true),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                onClick = { navHostController.navigate(Screen.Offices_resident_5.route) }) {
                Text(
                    text = "일반",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.width(80.dp))
            Button(modifier = Modifier
                .width(130.dp)
                .height(80.dp)
                .shadow(20.dp, shape = RectangleShape, clip = true),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                onClick = { /*다음페이지로 이동*/  }) {
                Text(
                    text = "면제신청",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    KioskTutorialTheme {
        var navCtrl = rememberNavController()
        officehome4(navCtrl)
    }
}