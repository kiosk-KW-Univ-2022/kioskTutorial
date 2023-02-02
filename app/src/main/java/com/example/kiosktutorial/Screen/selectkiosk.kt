package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.ui.theme.Typography
import com.example.kiosktutorial.ui.theme.KioskTutorialTheme
import com.example.kiosktutorial.ui.theme.backGround

@Composable
fun KioskHomeSelectionButton(navHostController: NavHostController) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        RecyclerViewContent1(navHostController)
    }
}

@Composable
fun RecyclerViewContent1(navHostController: NavHostController) {
    val kiosks = remember { HomeDataProvider.kioskList }
    LazyColumn(contentPadding = PaddingValues(30.dp, 8.dp)) {
        items(
            items = kiosks,
            itemContent = { KioskListItem(navHostController,it) }
        )
    }
}

@Composable
fun KioskListItem(navHostController:NavHostController,kiosk: Kioskicon) {
    Column {
        Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 12.dp),
        )
        Row {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .height(90.dp)
                    .width(90.dp)
                    .background(backGround)
                    .padding(15.dp)
                    .clickable {
                        navHostController.navigate(kiosk.route)
                    }
            ) {
                KioskImage(kiosk = kiosk)
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = kiosk.name, style = Typography.h6, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(3.dp)
            .background(Color.LightGray)){
            
        }
    }
}
@Composable
fun Secondhome(navHostController: NavHostController,  bExercise:Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(backGround)
                .padding(30.dp)
        ) {
            val paintD = painterResource(R.drawable._1_kt_wordmark__standard__01)
            Row(){
                Image(
                    painter = paintD,
                    contentDescription = "아이콘",
                    contentScale = ContentScale.Fit,

                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)

                )
                Box(modifier = Modifier.height(70.dp)
                ){
                    Text(
                        text= " 키오스크 교육용 체험 앱",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,

                        )
                }

            }


        }

        val context = LocalContext.current


        var toast: Toast? = null

        // selection
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backGround)
        ) {
            Box(modifier = Modifier
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
            ){
                KioskHomeSelectionButton(navHostController)
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
fun SecondhomePreview(){
    val navHostController = rememberNavController()
    Secondhome(navHostController, true)
}