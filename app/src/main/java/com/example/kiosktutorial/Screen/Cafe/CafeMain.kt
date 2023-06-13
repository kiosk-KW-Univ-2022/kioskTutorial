package com.example.kiosktutorial.Screen.Cafe

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.*
import com.example.kiosktutorial.ui.theme.Shapes
import com.example.kiosktutorial.ui.theme.backGround

class CafeMain(isTutorial: Boolean,navHostController: NavHostController? = null) : IKiosk(isTutorial) {
    var CafeColor = backGround
    override var tutorialStepDataList: Map<Int, TutorialStepData> =
        mutableMapOf(
            0 to TutorialStepData(description = "카페 키오스크 튜토리얼을 시작합니다\n시작을 하려면 화면을 눌러주세요",
                stateFunction = {}),
            1 to TutorialStepData(description = "카테고리에서 티를 눌러주세요.", stateFunction = {}),
            2 to TutorialStepData(description = "홍차를 눌러 주문을 해주세요.", stateFunction = {})
        )

    data class CafeMenuImg(//CafeMenu 에 대한이미지와 정보
        val name: String,
        val imageId: Int,
        val price: Int
    )
    data class Order(
        var Menu: CafeMenuImg,
        var detail:  MutableState<Int> = mutableStateOf(0),
        var cup: MutableState<Int> = mutableStateOf(0),
        var quantity: MutableState<Int> = mutableStateOf(0)
    )
    object CafeMenuProvider {//각 데이터를 저장하는 장소.
        val menuLists = listOf(//TODO 추후 변경 Grid 형식, 리스트 복잡도 개선 필요(row 디자인 변경 2개씩 Column (for-loop)
            listOf(
                CafeMenuImg("아메리카노(hot)", R.drawable.americano_hot, 1500),
                CafeMenuImg("아이스 아메리카노", R.drawable.americano_ice, 2000)
            ),
            listOf(
                CafeMenuImg("카페라떼(HOT)", R.drawable.latte_hot, 2500),
                CafeMenuImg("아이스 카페라떼", R.drawable.latte_ice, 3000)
            ),
            listOf(
                CafeMenuImg("홍차", R.drawable.blacktea, 3500),
                CafeMenuImg("오렌지 주스", R.drawable.orange, 3500)
            ),
            listOf(
                CafeMenuImg("녹차", R.drawable.greentea, 3500),
                CafeMenuImg("꽃차", R.drawable.tea, 3500)
            ),
            listOf(
                CafeMenuImg("샌드위치", R.drawable.sandwitch, 3000),
                CafeMenuImg("빵", R.drawable.bread, 2000)
            ),
            listOf(
                CafeMenuImg("쿠키", R.drawable.cookie, 1500),
                CafeMenuImg("머핀", R.drawable.muffin, 3000)
            )
        )
        var OrderList :MutableList<Order> = mutableListOf(Order(menuLists[0][0],mutableStateOf(1),mutableStateOf(1)))
    }

    var TotalOrder: Int by mutableStateOf(1) // 총 주문 수량 개수
    var Money: Int by mutableStateOf(0) // 총 주문 금액
    var Category: Int by mutableStateOf(0) // 메뉴 카테고리
    var ScreenNum: Int by mutableStateOf(2)// 0 초기화면 1 메뉴 2 상세 주문 5. 주문화면
    var Menu : CafeMenuImg by mutableStateOf(CafeMenuProvider.menuLists[0][0])

    //TODO when 으로 대체
    @Composable
    fun MainAct(navHostController: NavHostController? = null) {
        // 장바구니 추가시 TotalOrder 증가
        // CafeMenuProvider.OrderList.add(1,Order(CafeMenuProvider.menuLists[2][1],0, 1))// 상세주문
        Column(modifier = Modifier .clickable {
            //navHostController?.navigate(Screen.CafeKiosk.route)
        })
        {
            when(ScreenNum){
                0 -> StartView()//카페 시작화면
                1 ->{//카페 메인화면
                    Column(
                        modifier = Modifier
                            .background(backGround)
                    ){
                        ActMainTitle(title = "키페")
                        CategorySelect()
                        OrderHistory()
                        ShowListMenu(0)
                    }
                }
                2 ->{//상세주문 화면
                    DetailOrder(Menu)
                }
                3->{//결제 화면\
                    if(navHostController!=null) OrderSuccess(navHostController)
                }
                4->{

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCafeMain() {
    val kioskTest = CafeMain(true,null)
    kioskTest.Layout{
        kioskTest.MainAct()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCafeMainWithReal() {
    val kioskTest = CafeMain(false,null)
    kioskTest.Layout{
        kioskTest.MainAct()
    }
}
