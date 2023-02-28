package com.example.kiosktutorial.Screen.Kiosk

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.CafeMainScreen
import com.example.kiosktutorial.Screen.Screen

class KioskTutorialContainer{
    var count:Int = 0
    var route:String = ""
    lateinit var navHostController:NavHostController

    @Composable
    fun View(){
        when(route){
            Screen.CafeHome.route -> CafeItem()
        }
    }

    @Composable
    fun CafeItem(){
        var index by remember{mutableStateOf(0)}
        val list = arrayOf(
            Pair(R.drawable.cafe_0, "카페 키오스크 시작 화면입니다"),
            Pair(R.drawable.cafe_1, "키오스크를 누르면 위와 같이 기본 화면이 나옵니다."),
            Pair(R.drawable.cafe_2, "화면의 상단에서 음료의 종류를 선택할 수 있습니다."),
            Pair(R.drawable.cafe_3, "음료 종류를 선택한 후, 아래 음료 이미지를 터치해 주문을 진행할 수 있습니다."),
            Pair(R.drawable.cafe_4, "매장에서 마실지, 포장해 나갈 것인지 컵을 선택하고"),
            Pair(R.drawable.cafe_5, "음료의 크기를 정할 수 있습니다."),
            Pair(R.drawable.cafe_6, "선택이 완료되면 주문 완료 버튼을 눌러 다음 주문을 진행하거나 결제를 진행할 수 있습니다."),
            Pair(R.drawable.cafe_7, "주문 내역을 확인합니다. 주문할 음료 수량과 내가 결제할 금액을 확인합니다."),
            Pair(R.drawable.cafe_8, "주문 내용이 생각한 것과 일치한다면 주문 완료 버튼을 눌러 결제를 진행합니다."),
            Pair(R.drawable.cafe_9, "카드를 꽂고 결제가 완료될 때까지 대기합니다."),
            Pair(R.drawable.cafe_10, "주문이 완료되었다는 안내가 나오면\n주문번호를 확인합니다.")
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            val paintD = painterResource(id = list[index].first)
            Image(
                painter = paintD,
                contentDescription = null,
                contentScale  = ContentScale.Fit
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ){
                Text(
                    modifier = Modifier.padding(10.dp).fillMaxWidth().padding(10.dp)
                        .background(Color(0xf0ffffff)),
                    text = list[index].second,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Button(
                        modifier = Modifier.padding(10.dp, 0.dp),
                        onClick = {
                            if(index > 0 ) index--
                            else navHostController.popBackStack()
                        }
                    ){
                        Text(
                            "이전"
                        )
                    }
                    Button(
                        modifier = Modifier.padding(10.dp, 0.dp),
                        onClick = {
                            if(index + 1 < list.size ) index++
                            else{
                                navHostController.popBackStack(Screen.Home.route, inclusive = false)
                            }
                        }
                    ){
                        Text(
                            if(index + 1 == list.size) "완료" else "다음"
                        )
                    }
                }
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewTutorialContainerView(){
    val kioskContainer = KioskTutorialContainer()
    kioskContainer.navHostController = rememberNavController()
    kioskContainer.route = Screen.CafeHome.route
    kioskContainer.View()
}