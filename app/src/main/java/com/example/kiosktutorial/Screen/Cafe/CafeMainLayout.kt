package com.example.kiosktutorial.Screen.Cafe

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.MenuProvider
import androidx.navigation.NavHostController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.ActMainContent
import com.example.kiosktutorial.Screen.ActMainTitle
import com.example.kiosktutorial.Screen.Screen
import com.example.kiosktutorial.ui.theme.backGround
import kotlinx.coroutines.delay
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun CafeMain.StartView(){
    Column(modifier = Modifier
        .setMode(
            0,
            Modifier
                .alpha(1f)
                .background(Color(0xFFffe65a))
                .fillMaxSize()
        ) {
            ScreenNum = 1;
        }
        .fillMaxWidth()
        .paint(painterResource(id = R.drawable.cafeimg), contentScale = ContentScale.Crop)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                "KT 카페",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "아무곳이나 터치하여 \n 진행해 주세요",
                color = Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

    }
}
@Composable
fun CafeMain.CafeListItem(cafe: CafeMain.CafeMenuImg) {
    Column() {
        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .paint(painterResource(id = cafe.imageId), contentScale = ContentScale.Fit)
                .clickable {
                    ScreenNum = 2;
                    // 클릭시 상세 주문 화면으로 넘어가야함.
                    //navHostController.navigate("cafe_order/${cafe}")
                }
        ) {}
        Text(
            "${cafe.name}\n${cafe.price}원",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(20.dp))
    }
}
@Composable
fun CafeMain.CafeSelection(number: Int) {
    Row {
        RecyclerViewContent( number * 2)
        Box(modifier = Modifier.width(100.dp))
        RecyclerViewContent(number * 2 + 1)
    }
}
@Composable
fun CafeMain.ShowOrderMenu(i:CafeMain.Order){
    Box(modifier = Modifier
        .width(150.dp)
        .height(150.dp)
        .background(color = Color.White)
        .padding(2.dp)
    ){
        Column(horizontalAlignment =Alignment.CenterHorizontally) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .paint(
                    painterResource(id = i.Menu.imageId),
                    contentScale = ContentScale.Fit
                )
            )
            Text(text = "${i.Menu.name}")
            Row(modifier = Modifier.padding(5.dp)
                , verticalAlignment = Alignment.CenterVertically) {
                Button(modifier = Modifier
                    .height(50.dp)
                    .weight(1f) ,
                    onClick = {}) {
                    Text(modifier = Modifier.fillMaxSize(), text = "-",
                        fontWeight=FontWeight.Bold
                        ,color=Color.White
                        ,textAlign = TextAlign.Center,)
                }
                Text(modifier = Modifier.weight(1f)
                        .align(Alignment.CenterVertically),
                    text = "${i.quantity.value}",
                    textAlign = TextAlign.Center,
                )
                Button(modifier = Modifier
                    .height(50.dp)
                    .weight(1f) ,
                    onClick = {}) {
                    Text(modifier = Modifier.fillMaxSize(), text = "+",
                        fontWeight=FontWeight.Bold
                        ,color=Color.White
                        ,textAlign = TextAlign.Center,)
                }
            }
        }
    }
}
@Composable
fun CafeMain.DetailOrder(temp:CafeMain.CafeMenuImg){
    var item: CafeMain.Order= CafeMain.Order(temp,
    remember { mutableStateOf(0) },
    remember { mutableStateOf(1) },
    remember { mutableStateOf(1) })
    Column(
        modifier = Modifier
            .background(backGround)
    ){
        ActMainTitle(title = "키페")
        ActMainContent(){
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier= Modifier.height(100.dp)
                ){
                    Box(modifier = Modifier
                        .height(100.dp)
                        .weight(1f)
                        .paint(
                            painterResource(id = item.Menu.imageId),
                            contentScale = ContentScale.Fit
                        ))
                    Box(modifier = Modifier
                        .height(100.dp)
                        .weight(1f)){
                        Column {//가격 및 수량
                            Row(modifier = Modifier.weight(1f))// 위의 상품이름 및 전체 금액 출력 구문
                            {
                                Box(modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f)
                                ){
                                    Text(modifier= Modifier.fillMaxHeight(),
                                        text = "${item.Menu.name}\n${item.Menu.price}원",
                                        fontWeight= FontWeight.Bold
                                        ,textAlign = TextAlign.Start,)
                                }
                                Box(modifier = Modifier
                                    .fillMaxHeight()
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                                    .weight(1f)
                                ){
                                    Text(modifier= Modifier.fillMaxSize(),
                                        text = "총액\n${item.Menu.price * item.quantity.value}원",
                                        fontWeight= FontWeight.Bold
                                        ,textAlign = TextAlign.Center,)
                                }
                            }
                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ){
                                Button(modifier= Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = backGround),
                                    onClick = { /*TODO*/ }) {
                                    Text(text = "+")
                                }
                                Text(modifier= Modifier.weight(2f),
                                    text = "${item.quantity.value}",
                                    fontWeight= FontWeight.Bold
                                    ,textAlign = TextAlign.Center,)
                                Button(modifier= Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = backGround),
                                    onClick = { /*TODO*/ }) {
                                    Text(text = "-")
                                }
                            }
                        }

                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    Box(Modifier.weight(0.5f))
                    Button(modifier= Modifier.weight(3f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = if(item.cup.value==1) { Color.LightGray}else{
                            backGround
                        }),
                        onClick = { /*TODO*/ }) {
                        Text(text = "매장용컵")
                    }
                    Box(Modifier.weight(0.5f))
                    Button(modifier= Modifier.weight(3f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = if(item.cup.value==2) { Color.LightGray}else{
                            backGround
                        }),
                        onClick = { /*TODO*/ }) {
                        Text(text = "개인컵")
                    }
                    Box(Modifier.weight(0.5f))
                    Button(modifier= Modifier.weight(3f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = if(item.cup.value==3) { Color.LightGray}else{
                            backGround
                        }),
                        onClick = { /*TODO*/ }) {
                        Text(text = "일회용컵")
                    }
                    Box(Modifier.weight(0.5f))
                }
                if(item.cup.value>=1)
                {
                    Row(verticalAlignment = Alignment.CenterVertically)
                    {
                        Box(Modifier.weight(0.5f))
                        Button(modifier= Modifier.weight(3f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = if(item.detail.value==1) { Color.LightGray}else{
                                backGround
                            }),
                            onClick = { /*TODO*/ }) {
                            Text(text = "S")
                        }
                        Box(Modifier.weight(0.5f))
                        Button(modifier= Modifier.weight(3f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = if(item.detail.value==2) { Color.LightGray}else{
                                backGround
                            }),
                            onClick = { /*TODO*/ }) {
                            Text(text = "M")
                        }
                        Box(Modifier.weight(0.5f))
                        Button(modifier= Modifier.weight(3f),
                            colors = ButtonDefaults.buttonColors(backgroundColor = if(item.detail.value==3) { Color.LightGray}else{
                                backGround
                            }),
                            onClick = { /*TODO*/ }) {
                            Text(text = "L")
                        }
                        Box(Modifier.weight(0.5f))
                    }
                }
                if(item.detail.value>=1){
                    Button(modifier= Modifier.fillMaxWidth(0.9f),
                        colors = ButtonDefaults.buttonColors(backgroundColor = backGround),
                        onClick = { /*TODO Orderlist에 add하고 모드 변경*/ }) {
                        Text(text = "주문완료2")
                    }
                }

            }
        }
    }
}

@Composable
fun CafeMain.ShowListMenu(Page:Int){
    Row(modifier = Modifier.background(color=Color.Gray),
        verticalAlignment = Alignment.CenterVertically)
    {
        Box(modifier = Modifier.weight(1f))
        if(Page<TotalOrder)Box(modifier = Modifier.weight(5f)){ShowOrderMenu(i = CafeMain.CafeMenuProvider.OrderList[Page])}
        Box(modifier = Modifier.weight(1f))
        if(Page+1<TotalOrder)Box(modifier = Modifier.weight(5f)){ShowOrderMenu(i = CafeMain.CafeMenuProvider.OrderList[Page+1])}
        else Box(modifier = Modifier.weight(5f))
        Box(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CafeMain.RecyclerViewContent(number: Int) {
    val menuList = remember { CafeMain.CafeMenuProvider.menuLists[number] }
    LazyColumn(contentPadding = PaddingValues(0.dp, 0.dp)) {
        items(
            items = menuList,
            itemContent = { CafeListItem(it) }
        )
    }
}
@Composable
fun CafeMain.Categorybutton(number:Int){// 카테고리 number 0 커피, 1 티 ,2 디저트
    val Item = remember { CafeMenu.CategoryItemProvider.CategoryList[number] }
    if(Item.number ==Category)
        Box(modifier = Modifier.setMode(
            1,
            defaultModifier = Modifier.background(Color.Gray)
                .fillMaxHeight()
                .fillMaxWidth(0.2f)
        ){
            Category=number
        })
        {
            Text(
                text = "${Item.name}",
                color = if (Category == Item.number) Color.Black else Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    else{
        Box(modifier = Modifier.setMode(
            1,
            defaultModifier = Modifier.background(backGround)
                .fillMaxHeight()
                .fillMaxWidth(0.2f)
        ){
            Category=number
        })
        {
            Text(
                text = "${Item.name}",
                color = if (Category == Item.number) Color.Black else Color.White,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }
    }

}
@Composable
fun CafeMain.CategorySelect(){
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
        .height(400.dp)
        .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CafeSelection(Category)
    }

}
@Composable
fun CafeMain.OrderHistory() {
    Column(modifier = Modifier.background(color=Color.Gray))
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .height(15.dp),
                text = "주문내역",
                fontWeight = FontWeight.Bold
            )

        }
        Row(
            modifier = Modifier
                .height(150.dp)
                .padding(10.dp),
            Arrangement.Center
        )
        {
            ShowListMenu(0)
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(150.dp)
                .background(color = Color.Magenta)
        ) {

        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(150.dp)
                .background(color = Color.Black)
        ) {

        }

    }
    Row(
        modifier = Modifier.padding(5.dp)
    ) {
        Box(Modifier.weight(1f))
        Button(modifier = Modifier
            .weight(3f)
            .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(CafeColor),
            onClick = {

            }) {
            Text(text = "주문 취소", fontWeight = FontWeight.Bold)
        }
        Box(Modifier.weight(1f))
        Button(modifier = Modifier
            .weight(3f)
            .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(CafeColor),
            onClick = {
                ScreenNum = 5//주문완료

            }) {
            Text(text = "주문 완료", fontWeight = FontWeight.Bold)
        }
        Box(Modifier.weight(1f))
    }
}

@Composable
fun OrderSuccess(navHostController:NavHostController?=null){
    LaunchedEffect(key1 = true) {
        delay(3000)
        navHostController!!.popBackStack(Screen.Home.route, false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround)
    ) {
        Column(
            modifier = Modifier.align(alignment = Alignment.Center)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(), text = "주문이 성공적으로 진행되었습니다.",
                textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "주문번호는 100번 입니다.",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = androidx.compose.ui.text.TextStyle(
                    textDecoration = TextDecoration.Underline
                )
            )
            Spacer(modifier = Modifier.height(200.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "소요시간은 10분이 걸립니다.\n 안내번호가 뜰 경우 카운터로 오시면 됩니다.",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "놓고 가시는 물건이 없는지\n 확인하시기 바랍니다.",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

