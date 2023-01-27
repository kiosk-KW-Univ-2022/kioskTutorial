package com.example.kiosktutorial.Screen

import android.content.res.Configuration.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.draw.paint


var currentnumber =0
var orderfee =0
@Composable
fun CafeKiosk(navHostController: NavHostController,number:Int =0, fee:Int=0){
    var CafeColor = Color(0xFF28CDC8)
    var (Category, SetCategory) = remember{mutableStateOf(0)}

    currentnumber+=number
    orderfee+=fee
    Column(modifier = Modifier
        .fillMaxWidth()
        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = CafeColor))
        {}
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = CafeColor)
        ){
            Row(
                horizontalArrangement =  Arrangement.SpaceEvenly
            ){
                Box(modifier = Modifier.width(10.dp)){

                }
                Button(
                    colors = if(Category==0) ButtonDefaults.buttonColors(Color.White)
                    else ButtonDefaults.buttonColors(CafeColor),
                    onClick = { SetCategory(0) }
                ) {
                    Text(
                        text = "커피",
                        color = if(Category==0) Color.Black else Color.White,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold)
                }
                Box(modifier = Modifier.width(10.dp)){

                }
                Button(
                    colors =  if(Category==1) ButtonDefaults.buttonColors(Color.White)
                    else ButtonDefaults.buttonColors(CafeColor),
                    onClick = { SetCategory(1) }
                ) {
                    Text(
                        text = "티",
                        color = if(Category==1) Color.Black else Color.White,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold)
                }
                Box(modifier = Modifier.width(10.dp)){

                }
                Button(
                    colors =  if(Category==2) ButtonDefaults.buttonColors(Color.White)
                    else ButtonDefaults.buttonColors(CafeColor),
                    onClick = { SetCategory(2) }
                ) {
                    Text(
                        text = "디저트",
                        color =  if(Category==2) Color.Black else Color.White,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
        Box(//각 음료 나오는 곳들
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .background(color = Color.White)
                .padding(40.dp)
        ){
            if(Category==0)
                Column (){
                Row() {
                    Column() {
                        Box(modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .paint(
                                painterResource(id = R.drawable.americano_hot),
                                contentScale = ContentScale.Fit
                            )
                            .clickable { navHostController.navigate("cafe_order/${"아메리카노"}") }){
                        }
                        Text("아메리카노(hot)\n 1500원", fontWeight = FontWeight.Bold)
                    }
                    Box(modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)){
                    }
                    Column() {
                        Box(modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .paint(
                                painterResource(id = R.drawable.americano_ice),
                                contentScale = ContentScale.Fit
                            )
                            .clickable { navHostController.navigate("cafe_order/${"아이스 아메리카노"}") }){
                        }
                        Text("아메리카노(ice)\n 2000원", fontWeight = FontWeight.Bold)
                    }

                }
                Box(modifier = Modifier.height(50.dp)){}
                Row() {
                    Column() {
                        Box(modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .paint(
                                painterResource(id = R.drawable.latte_hot),
                                contentScale = ContentScale.Fit
                            )
                            .clickable { navHostController.navigate("cafe_order/${"카페라떼"}") }){
                        }
                        Text("카페라떼(hot)\n 2500원", fontWeight = FontWeight.Bold)
                    }
                    Box(modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)){
                    }
                    Column() {
                        Box(modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                            .paint(
                                painterResource(id = R.drawable.latte_ice),
                                contentScale = ContentScale.Fit
                            )
                            .clickable { navHostController.navigate("cafe_order/${"아이스 카페라떼"}") }){
                        }
                        Text("카페라떼(ice)\n 3000원", fontWeight = FontWeight.Bold)
                    }

                }
            }
            else if (Category==1)
                Column (){
                    Row() {
                        Column() {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .paint(
                                    painterResource(id = R.drawable.blacktea),
                                    contentScale = ContentScale.Fit
                                )
                                .clickable { navHostController.navigate("cafe_order/${"홍차"}") }){
                            }
                            Text("홍차\n 4500원", fontWeight = FontWeight.Bold)

                        }
                        Box(modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)){
                        }
                        Column() {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .paint(
                                    painterResource(id = R.drawable.orange),
                                    contentScale = ContentScale.Fit
                                )
                                .clickable { navHostController.navigate("cafe_order/${"오렌지 주스"}") }){
                            }
                            Text("오렌지 주스\n 4500원", fontWeight = FontWeight.Bold)
                        }

                    }
                    Box(modifier = Modifier.height(50.dp)){}
                    Row() {
                        Column() {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .paint(
                                    painterResource(id = R.drawable.tea),
                                    contentScale = ContentScale.Fit
                                )
                                .clickable { navHostController.navigate("cafe_order/${"꽃차"}") }){
                            }
                            Text("꽃차\n 4500원", fontWeight = FontWeight.Bold)
                        }
                        Box(modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)){
                        }
                        Column() {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .paint(
                                    painterResource(id = R.drawable.greentea),
                                    contentScale = ContentScale.Fit
                                )
                                .clickable { navHostController.navigate("cafe_order/${"녹차"}") }){
                            }
                            Text("녹차\n 4500원", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            else if (Category==2)
                Column (){
                    Row() {
                        Column() {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .paint(
                                    painterResource(id = R.drawable.sandwitch),
                                    contentScale = ContentScale.Fit
                                )
                                .clickable { navHostController.navigate("cafe_order/${"샌드위치"}") }){
                            }
                            Text("샌드위치\n 4000원", fontWeight = FontWeight.Bold)

                        }
                        Box(modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)){
                        }
                        Column() {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .paint(
                                    painterResource(id = R.drawable.bread),
                                    contentScale = ContentScale.Fit
                                )
                                .clickable { navHostController.navigate("cafe_order/${"빵"}") }){
                            }
                            Text("빵\n 3000원", fontWeight = FontWeight.Bold)
                        }

                    }
                    Box(modifier = Modifier.height(50.dp)){}
                    Row() {
                        Column() {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .paint(
                                    painterResource(id = R.drawable.cookie),
                                    contentScale = ContentScale.Fit
                                )
                                .clickable { navHostController.navigate("cafe_order/${"쿠키"}") }){
                            }
                            Text("쿠키\n 1500원", fontWeight = FontWeight.Bold)
                        }
                        Box(modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)){
                        }
                        Column() {
                            Box(modifier = Modifier
                                .height(100.dp)
                                .width(100.dp)
                                .paint(
                                    painterResource(id = R.drawable.muffin),
                                    contentScale = ContentScale.Fit
                                )
                                .clickable { navHostController.navigate("cafe_order/${"머핀"}") }){
                            }
                            Text("머핀\n 2000원", fontWeight = FontWeight.Bold)
                        }
                    }
                }
        }
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(color = Color.LightGray)
                .padding(10.dp)
            ){
                Text(modifier = Modifier.align(Alignment.TopStart),text = "주문내역", fontWeight = FontWeight.Bold)
                Text(modifier = Modifier.align(Alignment.CenterStart),text= "                총 $currentnumber ")
                Text(modifier = Modifier.align(Alignment.CenterEnd),text= "합계 $orderfee 원                    ")

            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(color = Color.LightGray)
                .padding(3.dp)
            ){
                Box(modifier = Modifier.width(40.dp)){

                }
                Button(modifier = Modifier.width(150.dp)
                    .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(CafeColor),
                    onClick = { /*TODO*/ }) {
                    Text(text = "주문 취소", fontWeight = FontWeight.Bold)
                }
                Box(modifier = Modifier.width(20.dp)){

                }
                Button(modifier = Modifier.width(150.dp)
                    .fillMaxHeight(),
                    colors = ButtonDefaults.buttonColors(CafeColor),
                    onClick = { /*TODO*/ }) {
                    Text(text = "주문 완료", fontWeight = FontWeight.Bold)
                }
            }

        }


    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewCafeKiosk(){
    val navCtrl = rememberNavController()
    CafeKiosk(navCtrl)
}