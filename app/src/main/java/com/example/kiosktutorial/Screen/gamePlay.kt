package com.example.kiosktutorial.Screen

import android.app.GameManager
import android.content.res.Configuration.*
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
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
import java.time.LocalDateTime
import java.util.*
import kotlin.concurrent.thread
import kotlin.random.Random
import kotlin.system.measureNanoTime

var num: Int = 4; //색깔의 종류
var colorname = arrayOf("검정","파랑","초록","빨강");//색상 리스트
var colorarray = arrayOf(Color.Black, Color.Blue, Color.Green, Color.Red)
var start : Long = 0

@Composable
fun GamePlayScreen(navHostController: NavHostController,title:String){
    start =System.currentTimeMillis()
    var timeout = 60
    val backgroundColor = Color(0xffffe690)
    if(title=="글자색 맞추기")
    {
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
                    .height(150.dp)
                    .background(Color(0xfffed55f))
            ) {
                val paintD = painterResource(R.drawable._1_kt_wordmark__standard__01)
    //            Image(painter = paintD, contentDescription = "")

                Text(
                    text = "\n글자의 색상을 맞춰 주세요.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.Center),
                    fontSize = 30.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

            }
            Column(
                modifier = Modifier
                    .background(backgroundColor)
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

                    var score = 0
                    Column(
                        Modifier.padding(top = 30.dp)

                    ) {
                        Text(
                            text = "남은 시간 $timeout 초",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "맞춘 숫자 : $score",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }

                }
            }

            val context = LocalContext.current
            val randomList = arrayOf(0,1,2,3)
            randomList.shuffle()
            Text(
                text = colorname[randomList[0]],
                color = colorarray[randomList[1]],
                fontSize = 70.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(Color(0x00000000))
            )
            //Button들
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color(0x00000000))
            ) {
                Row(
                    Modifier.padding(40.dp, 30.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .height(80.dp)
                            .width(130.dp),
                        border = BorderStroke(3.dp, Color.Black),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        shape = RoundedCornerShape(15.dp)

                    ) {
                        Text(
                            text = colorname[0],
                            fontSize = 20.sp
                        )
                    }
                    Button(
                        modifier = Modifier
                            .height(80.dp)
                            .width(130.dp)
                            .offset(65.dp),
                        border = BorderStroke(3.dp, Color.Black),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = colorname[1],
                            fontSize = 20.sp
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .background(Color(0x00000000))
            ) {
                Row(
                    Modifier.padding(40.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .height(80.dp)
                            .width(130.dp),
                        border = BorderStroke(3.dp, Color.Black),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = colorname[2],
                            fontSize = 20.sp
                        )
                    }
                    Button(
                        modifier = Modifier
                            .height(80.dp)
                            .width(130.dp)
                            .offset(65.dp),
                        border = BorderStroke(3.dp, Color.Black),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            text = colorname[3],
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
        var toast:Toast? = null
    }
    else if(title == "숫자 순서 맞추기")
    {
        var numberarray = arrayOf(1,2,3,4,5,6,7,8,9)
        numberarray.shuffle()
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
                    .height(150.dp)
                    .background(Color(0xfffed55f))
            ) {
                val paintD = painterResource(R.drawable._1_kt_wordmark__standard__01)
                //           Image(painter = paintD, contentDescription = "")

                Text(
                    text = "\n1~9까지 순서대로 눌러주세요",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.Center),
                    fontSize = 30.sp,
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
                    var number =1
                    Column(
                        Modifier
                            .padding(top = 30.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "남은 시간 $timeout 초",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "$number 를 눌러주세요",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )


                    }
                }

                Row(modifier= Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(20.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[0].toString() ,
                            fontSize = 20.sp
                        )
                    }
                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(20.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[1].toString(),
                            fontSize = 20.sp
                        )
                    }
                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(20.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[2].toString(),
                            fontSize = 20.sp
                        )
                    }
                }
                Row(modifier= Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(15.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[3].toString(),
                            fontSize = 20.sp
                        )
                    }
                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(15.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[4].toString(),
                            fontSize = 20.sp
                        )
                    }

                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(15.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[5].toString(),
                            fontSize = 20.sp
                        )
                    }
                }
                Row(modifier= Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(10.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[6].toString(),
                            fontSize = 20.sp
                        )
                    }
                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(10.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[7].toString(),
                            fontSize = 20.sp
                        )
                    }
                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .shadow(10.dp, shape = RectangleShape, clip = true),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
                        onClick = { /*TODO*/ }) {
                        Text(
                            text = numberarray[8].toString(),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
        var toast:Toast? = null
    }
}

@Preview
@Composable
fun PreviewGame(){
    val navCtrl = rememberNavController()
    GamePlayScreen(navHostController = navCtrl,"글자색 맞추기")
}

@Preview
@Composable
fun PreviewnumberGame(){
    val navCtrl = rememberNavController()
    GamePlayScreen(navHostController = navCtrl,"숫자 순서 맞추기")
}
