package com.example.kiosktutorial.Screen

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.kiosktutorial.R
import kotlinx.coroutines.delay

@Composable
fun AnimationSplashScreen(navController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    LaunchedEffect(key1 = true){
        startAnimation = true
        delay(2500)
        navController.popBackStack()
        navController.navigate(Screen.Home.route)
    }

    Splash(alphaAnim.value)

}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier
            .alpha(alpha)
            .fillMaxSize()
            .background(MaterialTheme.colors.background)

    ) {
        val paintD = painterResource(R.drawable.kwunivlogo)
        Image(
            painter = paintD,
            contentDescription = "학교 상징 이미지",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .size(200.dp)
        )

        Text(
            text="KT × 광운대학교 키오스크 교육앱"
        ,   modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .padding(
                    bottom = 50.dp
                )
        ,   textAlign = TextAlign.Center
        ,   color = MaterialTheme.colors.primary

        )

    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview(){
    Splash(1f)

}

//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun SplashDarkPreview() {
//    Splash(1f)
//
//}