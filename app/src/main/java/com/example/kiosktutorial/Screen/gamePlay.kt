package com.example.kiosktutorial.Screen

import android.app.GameManager
import android.content.res.Configuration.*
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
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



@Composable
fun GamePlay(name:String){
    if(name=="NumberGame") //숫자 순서 맞추기 게임
    {
        var num:Int =0;

    }
    else if(name == "ColorGame") //글자 색상 맞추기 게임
    {
        var num: Int = 4; //색깔의 종류
        var colorname = arrayOf("검정","파랑","초록","빨강");//색상 리스트

    }

}

@Composable
fun GamePlayScreen(navHostController: NavHostController){
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
                .height(200.dp)
                .background(Color(0xfffed55f))
        ) {
            val paintD = painterResource(R.drawable._1_kt_wordmark__standard__01)
//            Image(painter = paintD, contentDescription = "")

            Text(
                text= "아래의 글을 보고 글자 안의\n" +
                        " 색상을 알맞게 골라주세요 ",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0x00000000))

        ){
            val time= 60
            Text(
                text="남은시간 $time",
                textAlign = TextAlign.Center,
                modifier =  Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        val context = LocalContext.current
        val backgroundColor = Color(0xfffff0a3)
        Text(text = "검정" ,
            color = Color.Blue,
            fontSize =  70.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(Color(0x00000000))
        )
        //Button들
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Color(0x00000000))
        ){
            Row(
                Modifier.padding(40.dp, 30.dp)
            ) {
                Button(modifier = Modifier
                    .height(80.dp)
                    .width(130.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)
                ) {
                    Text(text = "파랑")
                }
                Button(modifier = Modifier
                    .height(80.dp)
                    .width(130.dp)
                    .offset(65.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)

                ) {
                    Text(text = "검정")
                }
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Color(0x00000000))
        ){
            Row(
                Modifier.padding(40.dp)
            ) {
                Button(modifier = Modifier
                    .height(80.dp)
                    .width(130.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)

                ) {
                    Text(text = "빨강")
                }
                Button(modifier = Modifier
                    .height(80.dp)
                    .width(130.dp)
                    .offset(65.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor)

                ) {
                    Text(text = "초록")
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
    GamePlayScreen(navHostController = navCtrl)
}
