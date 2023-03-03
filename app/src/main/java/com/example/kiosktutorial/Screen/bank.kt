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
fun Secondhome2(navHostController: NavHostController) {

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
            Row() {
                Image(
                    painter = paintD,
                    contentDescription = "아이콘",
                    contentScale = ContentScale.Fit,

                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)

                )
                Box(
                    modifier = Modifier.height(70.dp)
                ) {
                    Text(
                        text = " 은행",
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
            Box(
                modifier = Modifier
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
            ) {
                BankScreen(navHostController)
            }
        }
    }
}

@Composable
fun BankScreen(navHostController: NavHostController) {
    Column {

        Row(modifier = Modifier.padding(20.dp)) {
            Column {
                Button(
                    shape = RoundedCornerShape(5.dp),
                    onClick = { navHostController.navigate(Screen.Bank1.route) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGround
                    ),
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text(
                        "예금출금",
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
                        "입      금",
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
                        "계좌이체",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.width(60.dp))
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
                        "예금조회",
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
                        "통장정리",
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
                        "신용카드",
                        modifier = Modifier,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(
                        topStart = CornerSize(25.dp),
                        topEnd = CornerSize(25.dp),
                        bottomEnd = CornerSize(25.dp),
                        bottomStart = CornerSize(25.dp)
                    ), color = backGround
                )
                .height(400.dp)
                .width(380.dp)
                .padding(30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "만원/오만원/수표 출금 가능\n\t\t\t현금/수표 입금가능\n천원권 오처원권 입금불가",
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "\t\t\t\t\t\t\t\t[금융사기 예방 유의사항]\n국세청, 건강보험공단은 현금 입,출금기를 통하여 환급하는 경우는 없습니다.",
                fontSize = 19.sp,
                color = Color.Red
            )
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = "눈이 편한 ATM",
                fontSize = 30.sp
            )
        }
    }
}


@Preview
@Composable
fun bankPreview() {
    val navHostController = rememberNavController()
    Secondhome2(navHostController)
}