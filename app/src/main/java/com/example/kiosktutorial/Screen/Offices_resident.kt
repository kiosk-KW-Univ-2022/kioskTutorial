package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.ui.theme.Typography
import com.example.kiosktutorial.ui.theme.KioskTutorialTheme
import com.example.kiosktutorial.ui.theme.backGround

@Composable
fun officehome2(navHostController: NavHostController) {

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
                .height(100.dp)
                .background(backGround)
                .padding(30.dp)
        ) {
            val paintD = painterResource(R.drawable._1_kt_wordmark__standard__01)
            Spacer(modifier = Modifier.width(50.dp))
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
                        text= " 무인민원발급기",
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,

                        )
                    /*Button(onClick = { *//* 종료버튼 클릭 이벤트 *//* }) {
                        Text("종료")
                    }*/
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
                OfficeSelection2(navHostController)
            }
        }
    }
}
@Composable
fun OfficeSelection2(navHostController: NavHostController){
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        RecyclerViewContent2(navHostController)
    }
}


@Composable
fun RecyclerViewContent2(navHostController: NavHostController) {
    val offices = remember { DataProvider.residnetList }
    LazyColumn(contentPadding = PaddingValues(20.dp, 10.dp)) {
        items(
            items = offices,
            itemContent = { OfficeListItem2(it,navHostController) }
        )
    }
}

@Composable
fun OfficeListItem2(office: Office,navHostController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 12.dp)
            .clickable { if (office.name == "주민등록표(초본)") navHostController.navigate(Screen.Offices_resident_1.route) },
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Row {
            OfficeImage(office=office)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = office.name, style = Typography.h5)
                Text(text = office.content, style = Typography.body1, modifier = Modifier.padding(0.dp,2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    KioskTutorialTheme {
        var navCtrl = rememberNavController()
        officehome2(navCtrl)
    }
}
