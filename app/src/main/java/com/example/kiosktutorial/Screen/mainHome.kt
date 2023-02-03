package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.ui.theme.backGround


@Composable
fun MovButton(
    title: String = "test",
    icon: Int? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .width(120.dp)
                .height(120.dp)
                .background(backGround)
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            val _icon = painterResource(id = icon ?: R.drawable.ic_launcher_background)
            Image(
                painter = _icon,
                contentDescription = "$title 아이콘",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = title,
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(1f),
            fontSize = 26.sp,
            textAlign = TextAlign.Start
        )
    }
}


@Composable
fun ActMainTitle(
    title: String,
    subTitle: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .background(backGround)
            .padding(25.dp)
    ) {
        val ktMark = painterResource(id = R.drawable._1_kt_wordmark__standard__01)
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = ktMark,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
            )
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (subTitle.isNotEmpty())
            Spacer(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            )
        Text(
            text = subTitle,
            modifier = Modifier
                .fillMaxWidth(),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun Home(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        ActMainTitle(
            title = "키오스크 교육용 체험 앱",
            subTitle = "아래의 메뉴에서 하고싶은 체험을 골라주세요."
        )


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
                Column(
                    modifier = Modifier
                        .padding(15.dp)
                ) {
                    MovButton(
                        title = "사용 지침서",
                        icon = R.drawable.book,
                    ) {
                        navHostController.navigate(Screen.KioskTutorialSelection.route)
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Color.LightGray)
                    )

                    MovButton(
                        title = "실전연습",
                        icon = R.drawable.note
                    ) {
                        navHostController.navigate(Screen.KioskExerciseSelection.route)
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(Color.LightGray)
                    )

                    MovButton(
                        title = "뇌활력 게임",
                        icon = R.drawable.game
                    ) {
                        navHostController.navigate(Screen.GameHome.route)
                    }

                }
            }
        }
    }

}

@Preview
@Composable
fun HomePreview() {
    val navHostController = rememberNavController()
    Home(navHostController)
}