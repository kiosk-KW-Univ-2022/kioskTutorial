package com.example.kiosktutorial.Screen

import android.content.res.Configuration.*
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
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
import com.example.kiosktutorial.Screen.Kiosk.dSpacer
import com.example.kiosktutorial.ui.theme.backGround

@Composable
fun GameSelectionButton(
    title: String,
    modifier: Modifier? = null,
    desc: String? = null,
    icon: Int? = null,
    func: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .heightIn(min = 100.dp)
            .padding(all = 5.dp)
            .clickable(
                onClick = func
            )
            .background(MaterialTheme.colors.background)
            .composed { modifier ?: Modifier }, Alignment.TopStart
    ) {
        Row() {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .height(90.dp)
                    .width(90.dp)
                    .background(backGround),
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    Spacer(modifier = Modifier.height(10.dp))
                    if (title == "글자 색 맞추기") Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "가",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    else if (title == "숫자 순서 맞추기") Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "1",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }


            val size =

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(red = 255, green = 255, blue = 255, alpha = 100))
                        .padding(
                            all = 10.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {


                    Text(
                        text = title, fontSize = 20.sp
                    )


                    desc?.let {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = desc, fontSize = 14.sp, textAlign = TextAlign.Center
                        )
                    }
                }
        }
    }
}

@Composable
fun GameHomeScreen(navHostController: NavHostController) {
    val startPath = Screen.GameHome.route
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ActMainTitle(
            title = "키오스크 교육용 체험 앱",
//            subTitle = "아래의 메뉴에서 하고싶은 게임을 골라주세요."
        )

        val context = LocalContext.current
        val backgroundColor = Color(0xfffed55f)

        var toast: Toast? = null

        ActMainContent {
            GameSelectionButton("숫자 순서 맞추기", desc = "1부터 9까지의 순서에\n 맞추어 누르는 게임입니다.", func = {
                navHostController.navigate(Screen.NumberGame.route)

            })
            dSpacer()
            GameSelectionButton("글자 색 맞추기", desc = "글자안의 색상을 맞추는 게임입니다.", func = {
                navHostController.navigate(Screen.TextGame.route)
            })

        }

    }
}


@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewGameHome() {
    val navCtrl = rememberNavController()
    GameHomeScreen(navCtrl)
}


@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewDarkGameHome() {
    val navCtrl = rememberNavController()
    GameHomeScreen(navCtrl)
}

class ColorText {
    constructor(text: String, color: Int) {


    }
}