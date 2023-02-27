package com.example.kiosktutorial.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R

//TODO 확인과 뒤로가기 버튼

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {
        Box(modifier = modifier.height(200.dp)) {
            Image(
                modifier =Modifier
                    .fillMaxSize(),
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun fingerpoint(navHostController: NavHostController) {
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
                .height(140.dp)
                .background(Color(0xfffed55f))
        ) {
            Text(
                text = "지문인식기에 오른쪽 \n엄지 손가락을 올려주세요",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
                fontSize = 27.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Box(//위에 설명구문 박스
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "\t[왼손으로 전산지문을 등록하신 분은 \t왼손 엄지손가락을 대어 \t주십시오]",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
        val painter = painterResource(id = R.drawable.fingerprint)
        val painter2=painterResource(id = R.drawable.finger)
        val description = ""
        val title = ""
            Row(
                Modifier
                    .fillMaxWidth()
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(16.dp)
                ) {
                    ImageCard(
                        painter = painter,
                        contentDescription = description,
                        title = title
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    ImageCard(
                        painter = painter2,
                        contentDescription = description,
                        title = title
                    )
                }
            }
        Spacer(modifier = Modifier.height(50.dp))
        Row() {
            Spacer(modifier = Modifier.width(5.dp))
            Button(modifier = Modifier
                .width(120.dp)
                .height(80.dp)
                .shadow(20.dp, shape = RectangleShape, clip = true),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                onClick = { /*다음페이지로 이동*/ }) {
                Text(
                    text = "취소",
                    fontSize = 30.sp
                )
            }
            Spacer(modifier = Modifier.width(100.dp))
            Button(modifier = Modifier
                .width(120.dp)
                .height(80.dp)
                .shadow(20.dp, shape = RectangleShape, clip = true),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                onClick = { navHostController.navigate(Screen.Offices_resident_4.route)}) {
                Text(
                    text = "확인",
                    fontSize = 30.sp
                )
            }
        }
    }
}
@Preview
@Composable
fun Previewfinger(){
    val navHostController = rememberNavController()
    fingerpoint(navHostController)
}