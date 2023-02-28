package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.ui.theme.backGround

@Composable
fun Home(navHostController: NavHostController) {
    var toast: Toast? = null
    val context = LocalContext.current
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
        ActMainContent {

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

@Preview
@Composable
fun HomePreview() {
    val navHostController = rememberNavController()
    Home(navHostController)
}