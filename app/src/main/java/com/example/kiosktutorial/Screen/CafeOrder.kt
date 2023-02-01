package com.example.kiosktutorial.Screen

import android.content.res.Configuration.*
import android.os.CountDownTimer
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
import androidx.compose.ui.text.style.TextDecoration
import com.example.kiosktutorial.ui.theme.backGround
import java.time.format.TextStyle


@Composable
fun CafeOrder(navHostController: NavHostController)
{
    val mCountDown: CountDownTimer = object : CountDownTimer(3500, 1000) {
        override fun onTick(millisUntilFinished: Long) {
        }

        override fun onFinish() {
            //countdown finish
            navHostController.popBackStack(Screen.Home.route,false)
        }
    }
    mCountDown.start()
   Box(modifier = Modifier
       .fillMaxSize()
       .background(backGround)
   ){
       Column (
           modifier = Modifier.align(alignment = Alignment.Center)
       ){
           Text(modifier = Modifier.fillMaxWidth(),text = "주문이 성공적으로 진행되었습니다.",
               textAlign = TextAlign.Center , fontSize = 20.sp, fontWeight = FontWeight.Bold)
           Text(modifier = Modifier.fillMaxWidth(), text = "주문번호는 100번 입니다.", textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold, style = androidx.compose.ui.text.TextStyle(
               textDecoration = TextDecoration.Underline
           )
           )
           Spacer(modifier = Modifier.height(200.dp))
           Text(modifier = Modifier.fillMaxWidth(), text = "소요시간은 10분이 걸립니다.\n 안내번호가 뜰 경우 카운터로 오시면 됩니다.", textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)
           Spacer(modifier = Modifier.height(50.dp))
           Text(modifier = Modifier.fillMaxWidth(), text = "놓고 가시는 물건이 없는지\n 확인하시기 바랍니다.", textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)
       }
   }
}



@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewCafeOrder(){
    val navCtrl = rememberNavController()
    CafeOrder(navCtrl)
}