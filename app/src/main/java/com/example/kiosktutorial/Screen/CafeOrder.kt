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


@Composable
fun CafeOrder(navHostController: NavHostController,price: Int, name: String, painter :Int)
{
    var CafeColor = Color(0xFF28CDC8)
    var (totalcost, setcost) = remember {
        mutableStateOf(price)
    }
    var (number, setnumber) = remember {
        mutableStateOf(1)
    }
    var (cup,setcup) = remember {
        mutableStateOf(0)
    }
    var (size,setsize) = remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = CafeColor))
        {}
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = Color.White)
        ){
            Row(){
                Box(modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .paint(
                        painterResource(id = painter),
                        contentScale = ContentScale.Fit
                    )){
                }
                Column(modifier = Modifier
                    .padding(10.dp)) {
                    Box(modifier = Modifier.height(25.dp)){}
                    Text("$name ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text("총 $totalcost 원", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Row(){
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .width(40.dp)
                            .clickable {
                                if (number != 1) setnumber(number - 1)
                                if (totalcost != price) setcost(totalcost - price)
                            }
                        ){
                            Text(modifier = Modifier.align(Alignment.Center),
                                text = "-", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        }
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .width(30.dp)
                        ){
                            Text(modifier = Modifier.align(Alignment.Center), text = "$number", fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        }
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .width(40.dp)
                            .clickable {
                                setnumber(number + 1)
                                setcost(totalcost + price)
                            }
                        ){
                            Text(modifier = Modifier.align(Alignment.Center),
                                text = "+", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        Row(modifier = Modifier.padding(10.dp)){
            Button(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                colors = if(cup==1) ButtonDefaults.buttonColors(Color.LightGray) else ButtonDefaults.buttonColors(CafeColor) ,
                onClick = { setcup(1) }
            ) {
                Text("매장컵")
            }
            Box(modifier = Modifier.width(30.dp))
            Button(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                colors = if(cup==2) ButtonDefaults.buttonColors(Color.LightGray) else ButtonDefaults.buttonColors(CafeColor) ,
                onClick = { setcup(2) }) {
                Text("개인컵")
            }
            Box(modifier = Modifier.width(30.dp))
            Button(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                colors = if(cup==3) ButtonDefaults.buttonColors(Color.LightGray) else ButtonDefaults.buttonColors(CafeColor) ,
                onClick = {setcup(3)}){
                Text("일회용컵")
            }
        }
        if(cup!=0){
            Row(modifier = Modifier.padding(10.dp)) {
                Button(
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp),
                    colors = if (size == 1) ButtonDefaults.buttonColors(Color.LightGray) else ButtonDefaults.buttonColors(
                        CafeColor
                    ),
                    onClick = { setsize(1) }
                ) {
                    Text("S")
                }
                Box(modifier = Modifier.width(30.dp))
                Button(
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp),
                    colors = if (size == 2) ButtonDefaults.buttonColors(Color.LightGray) else ButtonDefaults.buttonColors(
                        CafeColor
                    ),
                    onClick = { setsize(2) }) {
                    Text("M")
                }
                Box(modifier = Modifier.width(30.dp))
                Button(
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp),
                    colors = if (size == 3) ButtonDefaults.buttonColors(Color.LightGray) else ButtonDefaults.buttonColors(
                        CafeColor
                    ),
                    onClick = { setsize(3) }) {
                    Text("L")
                }
            }
        }
        if(size!=0){
            Row(modifier = Modifier.padding(10.dp)) {
                Button(
                    modifier = Modifier
                        .width(360.dp)
                        .height(50.dp),
                    colors =  ButtonDefaults.buttonColors(CafeColor),
                    onClick = { /* 기존 화면으로 이동*/ }
                ) {
                    Text("S")
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewCafeOrder(){
    val navCtrl = rememberNavController()
    CafeOrder(navCtrl,1500,"아메리카노",R.drawable.americano_hot)
}
