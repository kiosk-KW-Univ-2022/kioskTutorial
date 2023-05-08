package com.example.kiosktutorial.Screen.Kiosk

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.*
import com.example.kiosktutorial.ui.theme.Typography
import com.example.kiosktutorial.ui.theme.backGround
import java.util.Locale.Category

data class CafeMenuImg(
    val name: String,
    val image: Int,
    val price: Int
)
object CafeMenuProvider {

    val menuList1 = listOf(
        CafeMenuImg("아메리카노(hot)", R.drawable.americano_hot, 1500),
        CafeMenuImg("아이스 아메리카노", R.drawable.americano_ice, 2000),
    )
    val menuList1_1 =listOf(
        CafeMenuImg("카페라떼(HOT)", R.drawable.latte_hot,2500),
        CafeMenuImg("아이스 카페라떼", R.drawable.latte_ice, 3000),
    )
    //홍차 오렌지 녹차 꽃차
    val menuList2 = listOf(
        CafeMenuImg("홍차", R.drawable.blacktea, 3500),
        CafeMenuImg("오렌지 주스", R.drawable.orange, 3500),
    )
    val menuList2_1 =listOf(
        CafeMenuImg("녹차", R.drawable.greentea,3500),
        CafeMenuImg("꽃차", R.drawable.tea, 3500),
    )
    val menuList3 = listOf(
        CafeMenuImg("샌드위치", R.drawable.sandwitch, 3000),
        CafeMenuImg("빵", R.drawable.bread, 2000),
    )
    val menuList3_1 =listOf(
        CafeMenuImg("쿠키", R.drawable.cookie,1500),
        CafeMenuImg("머핀", R.drawable.muffin, 3000),
    )
    val menuList = arrayOf(menuList1, menuList1_1, menuList2,menuList2_1, menuList3, menuList3_1)
}
@Composable
fun CafeSelection(navHostController: NavHostController,number:Int){
    Row(

    ) {
        RecyclerViewContent(navHostController,number*2)
        //Parser
        Box(modifier = Modifier.width(100.dp)){}
        RecyclerViewContent(navHostController,number*2+1)
    }
}
@Composable
fun RecyclerViewContent( navHostController: NavHostController , number:Int){
    val Cafes = remember { CafeMenuProvider.menuList[number] }
    LazyColumn(contentPadding = PaddingValues(0.dp, 0.dp)) {
        items(
            items = Cafes,
            itemContent = { CafeListItem(it, navHostController) }
        )
    }
}
@Composable
fun CafeListItem(cafe: CafeMenuImg, navHostController: NavHostController) {
    Column() {
        Box(modifier = Modifier.height(20.dp)){}
        Box(modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .paint(
                painterResource(id = cafe.image),
                contentScale = ContentScale.Fit
            )
            .clickable {
                navHostController.navigate("cafe_order/${cafe.name}")
            }) {
        }
        Text("${cafe.name}\n${cafe.price}원", fontWeight = FontWeight.Bold)
        // 수정 필요 , 최종 적인 공간
        Box(modifier = Modifier.height(20.dp)){}
    }
}
class CafeMenu(isTutorial: Boolean,navHostController: NavHostController? = null,TotalMoney:Int=0,TotalOrder:Int=0) : IKiosk(isTutorial) {
    var Category: Int by mutableStateOf(0)
    var CafeColor = backGround
    var Money:Int by mutableStateOf(TotalMoney)
    var Order:Int by mutableStateOf(TotalOrder)
    override var tutorialStepDataList: Map<Int, TutorialStepData> =
        mutableMapOf(
            0 to TutorialStepData("아이스 아메리카노를 눌러주세요!"),
            )
    data class CategoryData(
        var name: String,
        var number:Int
    )
    object CategoryItemProvider {
        val CategoryList = listOf(
            CategoryData("커피", 0),
            CategoryData("티", 1),
            CategoryData("디저트", 2)
        )
    }
    @Composable
    fun Categorybutton(number:Int){// 카테고리 number 0 커피, 1 티 ,2 디저트
        val Item = remember { CategoryItemProvider.CategoryList[number] }
        Button(
            colors = if (Item.number == Category) ButtonDefaults.buttonColors(Color.White)
            else ButtonDefaults.buttonColors(CafeColor),
            onClick = { Category=Item.number }
        ) {
            Text(
                text = "${Item.name}",
                color = if (Category == Item.number) Color.Black else Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
    @Composable
    fun MainAct(navHostController: NavHostController){
        //커피, 티, 디저트에 관한 키오스크
        var (currentnumber, Setcurrentnumber) = remember { mutableStateOf(0) }
        var (orderfee, Setorderfee) = remember { mutableStateOf(0) }
        var (name, Setname) = remember {
            mutableStateOf("")
        }
        var price = 0
        var (order, Setorder) = remember { mutableStateOf(false) }
        if (!order) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = CafeColor)
                )
                {} 
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = CafeColor)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Categorybutton(0);
                        Box(modifier = Modifier.width(10.dp)) {

                        }
                        Categorybutton(1);
                        Box(modifier = Modifier.width(10.dp)) {

                        }
                        Categorybutton(2);
                    }
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    CafeSelection(navHostController,Category)
                }
                Column() {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .background(color = Color.LightGray)
                            .padding(10.dp)
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.TopStart),
                            text = "주문내역",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = "                총 $currentnumber "
                        )
                        Text(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            text = "합계 $orderfee 원                    "
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .background(color = Color.LightGray)
                            .padding(10.dp),
                        Arrangement.Center
                    ) {
                        Button(modifier = Modifier
                            .width(150.dp)
                            .fillMaxHeight(),
                            colors = ButtonDefaults.buttonColors(CafeColor),
                            onClick = {
                                Setorderfee(0)
                                Setcurrentnumber(0)
                            }) {
                            Text(text = "주문 취소", fontWeight = FontWeight.Bold)
                        }
                        Box(Modifier.width(40.dp)){}
                        Button(modifier = Modifier
                            .width(150.dp)
                            .fillMaxHeight(),
                            colors = ButtonDefaults.buttonColors(CafeColor),
                            onClick = {
                                Setorderfee(0)
                                Setcurrentnumber(0)
                                // navHostController.navigate("${Screen.PayWindow.route}/${Screen.CafeOrder.route}")
                            }) {
                            Text(text = "주문 완료", fontWeight = FontWeight.Bold)
                        }
                    }

                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCafeMenu() {
    val kioskTest = CafeMenu(true,null)
    kioskTest.Layout{
        var navCtrl = rememberNavController()
       kioskTest.MainAct(navCtrl)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCafeMenuWithReal() {
    val kioskTest = CafeMenu(false,null)
    kioskTest.Layout{
        var navCtrl = rememberNavController()
        kioskTest.MainAct(navCtrl)
    }
}
