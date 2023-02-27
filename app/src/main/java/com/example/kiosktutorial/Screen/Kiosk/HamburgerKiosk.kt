package com.example.kiosktutorial.Screen

import android.content.res.Configuration.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import androidx.compose.ui.draw.paint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kiosktutorial.ui.theme.backGround


data class HamburgerMenu(
    var name: String,
    var price: Int,
    var image: Int
)

object HamburgerMenuProvider {
    val hamburgerList = listOf(
        HamburgerMenu("더블 베이컨 버거", 4500, R.drawable.bacon),
        HamburgerMenu("통새우 버거", 4500, R.drawable.shrimp),
    )
    val sideList = listOf(
        HamburgerMenu("감자튀김", 4500, R.drawable.hamburgerpotato),
        HamburgerMenu("치킨랩", 4500, R.drawable.chikenlab),
    )
    val setList = listOf(
        HamburgerMenu("더블 베이컨 버거 세트", 6500, R.drawable.baconset),
        HamburgerMenu("통새우 버거 세트", 6500, R.drawable.shrimpset),
    )
    val beverageList = listOf(
        HamburgerMenu("콜라", 4500, R.drawable.hamburgercoke),
        HamburgerMenu("오렌지 주스", 4500, R.drawable.hamburgerorangejuice),
    )
}

@Composable
fun hamburgerMenuUI(hamburgerMenu: HamburgerMenu) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = hamburgerMenu.image),
                contentScale = ContentScale.Fit
            )
    ) {
    }
}


@Composable
fun HambergurKiosk(navHostController: NavHostController) {
    var CafeColor = backGround
    var (Category, SetCategory) = remember { mutableStateOf(3) }
    var (order, Setorder) = remember { mutableStateOf(false) }
    var (currentnumber, Setcurrentnumber) = remember { mutableStateOf(0) }
    var (orderfee, Setorderfee) = remember { mutableStateOf(0) }
    var (name, Setname) = remember {
        mutableStateOf("")
    }
    var price = 0
    var painter = R.drawable.americano_hot
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
                ) {
                    Button(
                        modifier = Modifier.width(80.dp)
                            .height(50.dp),
                        colors = if (Category == 0) ButtonDefaults.buttonColors(Color.White)
                        else ButtonDefaults.buttonColors(CafeColor),
                        onClick = { SetCategory(0) }
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "단품",
                            color = if (Category == 0) Color.Black else Color.White,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        modifier = Modifier.width(80.dp)
                            .height(50.dp),
                        colors = if (Category == 1) ButtonDefaults.buttonColors(Color.White)
                        else ButtonDefaults.buttonColors(CafeColor),
                        onClick = { SetCategory(1) }
                    ) {
                        Text(
                            text = "세트",
                            color = if (Category == 1) Color.Black else Color.White,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        modifier = Modifier.width(80.dp)
                            .height(50.dp),
                        colors = if (Category == 2) ButtonDefaults.buttonColors(Color.White)
                        else ButtonDefaults.buttonColors(CafeColor),
                        onClick = { SetCategory(2) }
                    ) {
                        Text(
                            text = "음료",
                            color = if (Category == 2) Color.Black else Color.White,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Button(
                        modifier = Modifier.width(80.dp)
                            .height(50.dp),
                        colors = if (Category == 3) ButtonDefaults.buttonColors(Color.White)
                        else ButtonDefaults.buttonColors(CafeColor),
                        onClick = { SetCategory(3) }
                    ) {
                        Text(
                            text = "사이드",
                            color = if (Category == 3) Color.Black else Color.White,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Box(//각 음료 나오는 곳들
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .background(color = Color.White)
                    .padding(40.dp)
            ) {
                if (Category == 0) //메뉴 단품
                    Column() {
                        Row() {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clickable {
                                    Setorderfee(orderfee + HamburgerMenuProvider.hamburgerList[0].price)
                                    Setcurrentnumber(currentnumber + 1)
                                }) {
                                hamburgerMenuUI(HamburgerMenuProvider.hamburgerList[0])
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clickable {
                                    Setorderfee(orderfee + HamburgerMenuProvider.hamburgerList[1].price)
                                    Setcurrentnumber(currentnumber + 1)
                                }) {
                                hamburgerMenuUI(HamburgerMenuProvider.hamburgerList[1])
                            }
                        }
                    }
                else if (Category == 1)// 메뉴 세트
                    Column() {
                        Row() {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clickable {
                                    Setorderfee(orderfee + HamburgerMenuProvider.setList[0].price)
                                    Setcurrentnumber(currentnumber + 1)
                                }) {
                                hamburgerMenuUI(HamburgerMenuProvider.setList[0])
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clickable {
                                    Setorderfee(orderfee + HamburgerMenuProvider.setList[1].price)
                                    Setcurrentnumber(currentnumber + 1)
                                }) {
                                hamburgerMenuUI(HamburgerMenuProvider.setList[1])
                            }
                        }
                    }
                else if (Category == 2) // 메뉴 음료
                    Column() {
                        Row() {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clickable {
                                    Setorderfee(orderfee + HamburgerMenuProvider.beverageList[0].price)
                                    Setcurrentnumber(currentnumber + 1)
                                }) {
                                hamburgerMenuUI(HamburgerMenuProvider.beverageList[0])
                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clickable {
                                    Setorderfee(orderfee + HamburgerMenuProvider.beverageList[1].price)
                                    Setcurrentnumber(currentnumber + 1)
                                }) {
                                hamburgerMenuUI(HamburgerMenuProvider.beverageList[1])
                            }
                        }
                    }
                else if (Category == 3) // 메뉴 사이드
                    Column() {
                        Row() {
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clickable {
                                    Setorderfee(orderfee + HamburgerMenuProvider.sideList[0].price)
                                    Setcurrentnumber(currentnumber + 1)
                                }) {
                                hamburgerMenuUI(HamburgerMenuProvider.sideList[0])
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clickable {
                                    Setorderfee(orderfee + HamburgerMenuProvider.sideList[1].price)
                                    Setcurrentnumber(currentnumber + 1)
                                }) {
                                hamburgerMenuUI(HamburgerMenuProvider.sideList[1])
                            }
                        }
                    }
            }
            Column {
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
                        .padding(10.dp)
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
                    Box(modifier = Modifier.width(40.dp)) {

                    }
                    Button(modifier = Modifier
                        .width(150.dp)
                        .fillMaxHeight(),
                        colors = ButtonDefaults.buttonColors(CafeColor),
                        onClick = {
                            navHostController.navigate("${Screen.PayWindow.route}/${Screen.CafeOrder.route}")
                        }) {
                        Text(text = "주문 완료", fontWeight = FontWeight.Bold)
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


@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewHambergurKiosk() {
    val navCtrl = rememberNavController()
    HambergurKiosk(navCtrl)
}


