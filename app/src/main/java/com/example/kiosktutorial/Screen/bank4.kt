package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.kiosktutorial.ui.theme.backGround

@Composable
fun Secondhome4(navHostController: NavHostController) {
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
                        text= " 은행",
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
                BankScreen4(navHostController)
            }
        }
    }
}

@Composable
fun BankScreen4(navHostController: NavHostController) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))

        Box(modifier = Modifier
            .background(
                shape = RoundedCornerShape(
                    topStart = CornerSize(25.dp),
                    topEnd = CornerSize(25.dp),
                    bottomEnd = CornerSize(25.dp),
                    bottomStart = CornerSize(25.dp)
                ), color = Color.LightGray
            )
            .height(300.dp)
            .width(380.dp)
            .padding(30.dp)){
            Text(modifier = Modifier.align(Alignment.TopCenter),
                fontWeight = FontWeight.Bold,
                color= Color.Blue,
                text = "찾으시는 금액의 내용입니다", fontSize = 25.sp)
            Text(modifier = Modifier.align(Alignment.Center),text = "현금:\t\t\t\t\t 5만원\n수표:\t\t\t\t\t 0만원",
                fontSize = 25.sp)
            Text(modifier = Modifier.align(Alignment.BottomCenter), text = "합계금액:\t\t\t\t\t 5만원", fontSize = 25.sp)


        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .width(90.dp)
            .height(80.dp)
            .shadow(20.dp, shape = RectangleShape, clip = true),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
            onClick = { navHostController.navigate(Screen.Bank5.route) }) {
            Text(
                text = "확인",
                fontSize = 30.sp
            )
        }
    }
}

@Preview
@Composable
fun bankPreview4(){
    val navHostController = rememberNavController()
    Secondhome4(navHostController)
}