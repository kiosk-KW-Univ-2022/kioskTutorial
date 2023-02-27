package com.example.kiosktutorial.Screen

import android.content.res.Configuration
import android.os.CountDownTimer
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Paywindow(navHostController: NavHostController, route: String) {
    LaunchedEffect(key1 = true) {
        delay(2500)
        navHostController.popBackStack()
        navHostController.navigate(route)
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "신용카드를 투입구에 끝까지 넣어주세요. ",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .padding(20.dp)
        ) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.credit),
                contentDescription = ""
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "결제 중입니다. 잠시만 기다려 주세요.",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.padding(20.dp)) {
            AnimationGaugeBar(navHostController, 100, backGround, route)
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
            if (curValue.value == 100) {
//                navHostController.navigate(route)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Prepaywindow() {
    val navCtrl = rememberNavController()
    Paywindow(navCtrl, Screen.KioskExercise.route)
}
