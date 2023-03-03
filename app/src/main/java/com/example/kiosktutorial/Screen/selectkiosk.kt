package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.ui.theme.Typography
import com.example.kiosktutorial.ui.theme.backGround

@Composable
fun KioskHomeSelectionButton(navHostController: NavHostController, bExercise: Boolean) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        RecyclerViewContent1(navHostController, bExercise)
    }
}

@Composable
fun RecyclerViewContent1(navHostController: NavHostController, bExercise: Boolean) {
    val kiosks = remember { HomeDataProvider.kioskList }
    LazyColumn(contentPadding = PaddingValues(30.dp, 8.dp)) {
        items(
            items = kiosks,
            itemContent = { KioskListItem(navHostController, it, bExercise) }
        )
    }
}

@Composable
fun KioskListItem(navHostController: NavHostController, kiosk: Kioskicon, bExercise: Boolean) {
    val context = LocalContext.current
    var toast: Toast? = null
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (kiosk.route.isEmpty() || navHostController.findDestination(kiosk.route) == null) {
                        toast?.cancel()
                        toast = Toast.makeText(context, "잘못된 경로입니다.", Toast.LENGTH_SHORT)
                        toast!!.show()
                        return@clickable
                    }

                    if(bExercise)
                        navHostController.navigate((kiosk.route))
                    else
                        navHostController.navigate("${Screen.KioskTutorialContainer.route}/${kiosk.route}")
                }
                .padding(5.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(90.dp)
                    .height(90.dp)
                    .background(backGround)
                    .padding(15.dp)
            ) {
                KioskImage(kiosk = kiosk)
            }

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = kiosk.name,
                    style = Typography.h6,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(
            modifier = Modifier
                .padding(0.dp, 10.dp)
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.LightGray)
        )

    }

}

@Composable
fun SecondHome(navHostController: NavHostController, bExercise: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        ActMainTitle(
            title = "키오스크 교육용 체험 앱",
            subTitle = "아래에서 체험하고싶은 키오스크를 골라주세요"
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
                KioskHomeSelectionButton(navHostController, bExercise)
            }

        }

    }

}

@Composable
fun KioskImage(kiosk: Kioskicon) {
    var paintD = painterResource(id = kiosk.image)
    Image(
        painter = paintD,
        contentDescription = null,
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .height(80.dp)
            .width(80.dp)
    )
}

@Preview
@Composable
fun SecondhomePreview() {
    val navHostController = rememberNavController()
    SecondHome(navHostController, true)
}