package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

//TODO topappbar로 변경하기

@Composable
fun Secondhome3(navHostController: NavHostController) {
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
                   /* Button(onClick = { *//* 종료버튼 클릭 이벤트 *//* }) {
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
                BankScreen3(navHostController)

            }
        }
    }
}

@Composable
fun BankScreen3(navHostController: NavHostController) {
    Column {

        Row(modifier = Modifier.padding(20.dp)) {
            Column {
                Button(
                    shape = RoundedCornerShape(5.dp), onClick = { navHostController.navigate(Screen.Bank4.route)},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround,
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        " 1만원 ",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
                Button(
                    shape = RoundedCornerShape(5.dp), onClick = { /* 왼쪽 버튼2 클릭 이벤트 */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        " 3만원 ",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    shape = RoundedCornerShape(5.dp), onClick = { /* 왼쪽 버튼3 클릭 이벤트 */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        " 5만원 ",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    shape = RoundedCornerShape(5.dp), onClick = { /* 왼쪽 버튼3 클릭 이벤트 */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        "10만원",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.width(130.dp))
            // 오른쪽 버튼
            Column {
                Button(
                    shape = RoundedCornerShape(5.dp), onClick = { /* 오른쪽 버튼1 클릭 이벤트 */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        "20만원",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    shape = RoundedCornerShape(5.dp), onClick = { /* 오른쪽 버튼2 클릭 이벤트 */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        "25만원",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    shape = RoundedCornerShape(5.dp), onClick = { /* 오른쪽 버튼3 클릭 이벤트 */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        "30만원",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    shape = RoundedCornerShape(5.dp), onClick = { /* 오른쪽 버튼3 클릭 이벤트 */ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        "  기타  ",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(modifier = Modifier
            .background(
                shape = RoundedCornerShape(
                    topStart = CornerSize(25.dp),
                    topEnd = CornerSize(25.dp),
                    bottomEnd = CornerSize(25.dp),
                    bottomStart = CornerSize(25.dp)
                ), color = backGround
            )
            .height(300.dp)
            .width(380.dp)
            .padding(30.dp)){
            Text(modifier = Modifier.align(Alignment.TopCenter),text = "찾으실 금액의 버튼을\n하나만 눌러주십시오.", fontSize = 20.sp)
            Text(modifier = Modifier.align(Alignment.Center),text = "ATM 인출한도",
                fontSize = 25.sp, color = Color.Blue)
            Text(modifier = Modifier.align(Alignment.BottomCenter), text = "전액 현금: 100만원\n전액 수표:500\n현금+수표:500", fontSize = 20.sp)
        }
    }
}





@Preview
@Composable
fun bankPreview3(){
    val navHostController = rememberNavController()
    Secondhome3(navHostController)
}