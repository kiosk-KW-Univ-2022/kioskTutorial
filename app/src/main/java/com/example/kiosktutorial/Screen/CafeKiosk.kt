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
fun CafeKiosk(navHostController: NavHostController,number:Int =0, fee:Int=0){
    var CafeColor = Color(0xFF28CDC8)
    var (Category, SetCategory) = remember{mutableStateOf(0)}
    var (order,Setorder) =remember { mutableStateOf(false)}
    var (currentnumber,Setcurrentnumber) = remember { mutableStateOf(0)}
    var (orderfee:Int,Setorderfee) = remember{ mutableStateOf(0) }
    var (name,Setname)= remember {
        mutableStateOf("")
    }
    var price=0
    var painter = R.drawable.americano_hot
    currentnumber+=number
    orderfee+=fee
    if(!order)
    {
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
                if(Category==0) //메뉴 커피
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
                                    .clickable {
                                        Setname( "아메리카노")
                                        Setorder(true)
                                        //   navHostController.navigate("cafe_order/${"아메리카노"}")
                                    }){
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
                                    .clickable {
                                        Setname("아이스 아메리카노")
                                        Setorder(true)
                                        // navHostController.navigate("cafe_order/${"아이스 아메리카노"}")
                                    }){
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
                                    .clickable {
                                        Setname ( "카페라떼")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"카페라떼"}")
                                    }){
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
                                    .clickable {
                                        Setname ( "아이스 카페라떼")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"아이스 카페라떼"}")
                                    }){
                                }
                                Text("카페라떼(ice)\n 3000원", fontWeight = FontWeight.Bold)
                            }

                        }
                    }
                else if (Category==1)// 메뉴 티
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
                                    .clickable {
                                        Setname ( "홍차")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"홍차"}")
                                    }){
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
                                    .clickable {
                                        Setname ( "오렌지 주스")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"오렌지 주스"}")
                                    }){
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
                                    .clickable {
                                        Setname ( "꽃차")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"꽃차"}")
                                    }){
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
                                    .clickable {
                                        Setname ( "녹차")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"녹차"}")
                                    }){
                                }
                                Text("녹차\n 4500원", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                else if (Category==2) // 메뉴 디저트
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
                                    .clickable {
                                        Setname("샌드위치")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"샌드위치"}")
                                    }){
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
                                    .clickable {
                                        Setname ("빵")
                                        Setorder(true)
//                                        navHostController.navigate("cafe_order/${"빵"}")
                                    }){
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
                                    .clickable {
                                        Setname ( "쿠키")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"쿠키"}")
                                    }){
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
                                    .clickable {
                                        Setname ( "머핀")
                                        Setorder(true)
                                        //navHostController.navigate("cafe_order/${"머핀"}")
                                    }){
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
                    Box(modifier = Modifier.width(20.dp)){

                    }
                    Button(modifier = Modifier
                        .width(150.dp)
                        .fillMaxHeight(),
                        colors = ButtonDefaults.buttonColors(CafeColor),
                        onClick = { /*TODO*/ }) {
                        Text(text = "주문 완료", fontWeight = FontWeight.Bold)
                    }
                }

            }


        }
    }
    else
    {
        if(name=="아메리카노") price =1500
        else if(name == "아이스 아메리카노") price= 2000
        else if(name=="카페라떼") price=2500
        else if(name=="아이스 카페라떼") price=3000
        else if(name=="홍차") price=4500
        else if(name=="꽃차") price=4500
        else if(name=="녹차") price=4500
        else if(name=="오렌지 주스") price=4500
        else if(name=="샌드위치") price=4000
        else if(name=="빵") price= 3000
        else if(name=="쿠키") price=1500
        else if(name=="머핀") price=2000
        else price =0
        if(name=="아메리카노")               painter=R.drawable.americano_hot
        else if(name == "아이스 아메리카노")  painter=R.drawable.americano_ice
        else if(name=="카페라떼")           painter=R.drawable.latte_hot
        else if(name=="아이스 카페라떼")      painter=R.drawable.latte_ice
        else if(name=="홍차")             painter=R.drawable.blacktea
        else if(name=="꽃차")             painter=R.drawable.tea
        else if(name=="녹차")             painter=R.drawable.greentea
        else if(name=="오렌지 주스")         painter=R.drawable.orange
        else if(name=="샌드위치")            painter=R.drawable.sandwitch
        else if(name=="빵")               painter=R.drawable.bread
        else if(name=="쿠키")              painter=R.drawable.cookie
        else if(name=="머핀")              painter=R.drawable.muffin

        var dessert =0
        if(name=="샌드위치")           dessert=1
        else if(name=="빵")          dessert=1
        else if(name=="쿠키")         dessert=1
        else if(name=="머핀")         dessert=1

        var CafeColor = Color(0xFF28CDC8)
        var (totalcost, setcost) = remember {
            mutableStateOf(price)
        }
        var (number:Int, setnumber) = remember {
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
                                .width(34.dp)
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
            if(dessert==0){
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
                }//컵 선택
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
                }//사이즈 선택
            }
            if(size!=0||dessert==1){ //최종 주문 완료 구문
                Row(modifier = Modifier.padding(10.dp)) {
                    Button(
                        modifier = Modifier
                            .width(360.dp)
                            .height(50.dp),
                        colors =  ButtonDefaults.buttonColors(CafeColor),
                        onClick = {
                            Setcurrentnumber(currentnumber+number)
                            Setorderfee(orderfee+totalcost)
                            Setorder(!order)
                            //navHostController.navigate("cafe_kiosk/${number}/${totalcost}")
                            }
                    ) {
                        Text("주문완료")
                    }
                }
            }
        }
    }


}

//data class CafeMenu(
//    var name:String,
//    var price: Int,
//    var Picture:Int
//)
//data class CafeOrder(
//    var Menu:CafeMenu,
//    var number:Int,
//)
//
//class MainViewModel: ViewModel(){
//    private val _order_number = mutableStateOf(0)
//    val order_number: State<Int> = _order_number
//    private val _order_price = mutableStateOf(0)
//    val order_price = _order_price
//
//    fun changeValue(num1:Int ,num2:Int)
//    {
//        _order_number.value += num1
//        _order_price.value += num2
//    }
//
//}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewCafeKiosk(){
    val navCtrl = rememberNavController()
    CafeKiosk(navCtrl)
}


