package com.example.kiosktutorial.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiosktutorial.R
import com.example.kiosktutorial.ui.theme.backGround

@Composable
fun ActMainContent( innerContent:@Composable() ()->Unit){
    val cornerRounded = CornerSize(25.dp)
    Column(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(
                    topStart = cornerRounded,
                    topEnd = cornerRounded,
                    bottomStart = cornerRounded,
                    bottomEnd = cornerRounded
                ), color = Color.White
            )
            .padding(10.dp)
            .fillMaxSize()
    ){
        innerContent()
    }
}

@Composable
fun MovButton(
    title: String = "test",
    icon: Int? = null,
    iconSize:Float = 1.0f,
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
                .width((120 * iconSize).dp)
                .height((120 * iconSize).dp)
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
    subTitle: String = "",
    fixedHeight:Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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

        if (subTitle.isNotEmpty() || fixedHeight){
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
}


@Preview(showBackground = true)
@Composable
fun PreviewActMainTitle(){
    Column(
        modifier = Modifier
            .background(backGround)
    ){
        ActMainTitle(title = "테스트 페이지")
        ActMainContent(){
            // content
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewActMainTitleWithFixedHeight(){
    Column(
        modifier = Modifier
            .background(backGround)
    ){
        ActMainTitle(title = "고정 높이 테스트 페이지", fixedHeight = true)
        ActMainContent(){
            // content
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActMainTitleWithSubTitle(){
    Column(
        modifier = Modifier
            .background(backGround)
    ){
        ActMainTitle(title = "부제목 테스트 페이지", subTitle="Description")
        ActMainContent(){
            //content
        }
    }
}
