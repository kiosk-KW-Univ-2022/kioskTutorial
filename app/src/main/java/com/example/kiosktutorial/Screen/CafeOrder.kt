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
                    Text("$name ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Box(modifier = Modifier
                        .height(50.dp)){}
                    Text("$totalcost 원", fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
