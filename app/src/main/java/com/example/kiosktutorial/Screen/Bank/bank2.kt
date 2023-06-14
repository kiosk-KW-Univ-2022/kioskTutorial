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


@Composable
fun InputNumber(navHostController: NavHostController) {
    var (number, setnumber) = remember {
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
                text = "카드 비밀번호 4자리를 눌러주세요",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
                fontSize = 20.sp,
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
                    .height(120.dp)
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
                var (text1, Settext1) = remember {
                    mutableStateOf("-")
                }
                var (text2, Settext2) = remember {
                    mutableStateOf("-")
                }
                var (text3, Settext3) = remember {
                    mutableStateOf("-")
                }
                var (text4, Settext4) = remember {
                    mutableStateOf("-")
                }
                if (number >= 1) Settext1("*") else Settext1("-")
                if (number >= 2) Settext2("*") else Settext2("-")
                if (number >= 3) Settext3("*") else Settext3("-")
                if (number >= 4) Settext4("*") else Settext4("-")
                Column(
                    Modifier
                        .padding(top = 30.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "비밀번호 :  ${text1}  ${text2}  ${text3}  ${text4} ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "   비밀번호는 타인이나 카메라 등에 노출되지 않도록 손이나 책 등으로 가린 후 입력해주세요",
                        fontSize = 16.sp, color = Color.Red
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "1",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
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
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "3",
                        fontSize = 30.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "4",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "5",
                        fontSize = 30.sp
                    )
                }

                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "6",
                        fontSize = 30.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "7",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
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
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "9",
                        fontSize = 30.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(number - 1) }) {
                    Text(
                        text = "삭제",
                        fontSize = 20.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "0",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(0) }) {
                    Text(
                        text = "정정",
                        fontSize = 20.sp
                    )
                }
            }
            if (number == 4) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(80.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true)
                    .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                    onClick = { navHostController.navigate(Screen.Bank3.route) }) {
                    Text(
                        text = "확인",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun Previewnumber() {
    val navHostController = rememberNavController()
    InputNumber(navHostController)
}