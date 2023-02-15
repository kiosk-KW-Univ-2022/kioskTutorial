package com.example.kiosktutorial.Screen

import android.os.CountDownTimer
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.ui.theme.backGround


@Composable
fun Paywindow2(navHostController: NavHostController, route: String) {
    val mCountDown: CountDownTimer = object : CountDownTimer(2500, 1000) {
        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            //countdown finish
            navHostController.navigate(route)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        Box(
            modifier = Modifier
                .background(backGround)
                .fillMaxWidth()
                .height(50.dp)
        )
        {
        }
        Spacer(modifier = Modifier.height(100.dp))

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
            Text(modifier = Modifier.align(Alignment.Center),
                text = "정상적으로 처리되었습니다.\n놓고 가시는 물건이 없는지 확인하시기 바랍니다.", fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center)
        }

       Box(modifier = Modifier.padding(20.dp)){
            mCountDown.start()
            AnimationGaugeBar(navHostController,100, backGround,route)
        }


    }

}
@Composable
fun AnimationGaugeBar(
    navHostController: NavHostController,
    value: Int, // 표시할 값
    color: Color, // 게이지바의 색
    route: String // 넘어갈 페이지
) {
    var animationPlayed by remember { //애니메이션 트리거를 위한 boolean 값
        mutableStateOf(false)
    }
    val curValue = animateIntAsState(
        targetValue = if (animationPlayed) value else 0,
        animationSpec = tween(
            durationMillis = 2000,
            delayMillis = 0
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .clip(CircleShape)
            .background(Color.LightGray)

    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(curValue.value / 100f)
                .clip(CircleShape)
                .background(color = color)
                .padding(8.dp)
        ) {
            Text(text = curValue.value.toString())
            if(curValue.value==100)
            {
               navHostController.navigate(route)
            }
        }
    }
}

@Preview()
@Composable
fun Prepaywindow(){
    val navHostController = rememberNavController()
    Paywindow2(navHostController, Screen.KioskExercise.route)
}