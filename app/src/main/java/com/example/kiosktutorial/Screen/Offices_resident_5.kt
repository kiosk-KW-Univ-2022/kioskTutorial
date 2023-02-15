package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


//TODO 삭제 정정 눌렀을때 *모양이 -로 바뀌도록
@Composable
fun InputNumber3(navHostController: NavHostController)
{
    var (number,setnumber) = remember {
        mutableStateOf(0)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(//위에 설명구문 박스
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xfffed55f))
        ) {
            Text(
                text = "발급할 부수를 입력한 후 \n수수료를 투입해 주세요",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }
        Column(
            modifier = Modifier
                .background(Color.White),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        shape = RoundedCornerShape(
                            topStart = CornerSize(25.dp),
                            topEnd = CornerSize(25.dp),
                            bottomEnd = CornerSize(0),
                            bottomStart = CornerSize(0),
                        ),
                        color = Color.White
                    )
            ) {
                
                Column(
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "현재금액         ${number*200}  원",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "발급부수         ${number}  부",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(modifier= Modifier
                .padding(10.dp)
                .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(1) }) {
                    Text(
                        text = "1" ,
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(2)}) {
                    Text(
                        text = "2",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(3) }) {
                    Text(
                        text =  "3",
                        fontSize = 30.sp
                    )
                }
            }
            Row(modifier= Modifier
                .padding(10.dp)
                .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(4) }) {
                    Text(
                        text =  "4",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(5) }) {
                    Text(
                        text =  "5",
                        fontSize = 30.sp
                    )
                }

                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(6) }) {
                    Text(
                        text =  "6",
                        fontSize = 30.sp
                    )
                }
            }
            Row(modifier= Modifier
                .padding(10.dp)
                .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(7) }) {
                    Text(
                        text =  "7",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = {setnumber(8)}) {
                    Text(
                        text = "8",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(9) }) {
                    Text(
                        text =  "9",
                        fontSize = 30.sp
                    )
                }
            }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(80.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true)
                    .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                    onClick = { navHostController.navigate(Screen.DoneApp.route) }) {
                    Text(
                        text = "확인",
                        fontSize = 30.sp
                    )
                }
        }
    }
}


@Preview
@Composable
fun Previewnumber3(){
    val navHostController = rememberNavController()
    InputNumber3(navHostController)
}