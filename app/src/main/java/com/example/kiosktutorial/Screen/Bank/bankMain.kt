package com.example.kiosktutorial.Screen.Bank

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiosktutorial.Screen.*
import com.example.kiosktutorial.Screen.Kiosk.dSpacer
import com.example.kiosktutorial.ui.theme.backGround
import java.time.LocalDate

class BankMain(isTutorial:Boolean, step: Int=0): IKiosk(isTutorial,step){
    override var tutorialStepDataList: Map<Int, TutorialStepData> = mutableMapOf(
        0 to TutorialStepData(
            description = "은행 키오스크 튜토리얼을 시작합니다\n시작을 하려면 화면을 눌러주세요"
        ),
        1 to TutorialStepData(
            description = "화면의 예금출금을 눌러주세요",
        ),
        2 to TutorialStepData(
            description = "화면의 카드출금을 눌러주세요",
        ),
        3 to TutorialStepData(
            description = "확인 버튼을 눌러주세요",
        ),
        4 to TutorialStepData(
            description = "비밀번호 4자리를 눌러주세요",
        ),
        5 to TutorialStepData(
            description = "확인버튼을 눌러주세요"
        ),
        6 to TutorialStepData(
            description = "1만원을 눌러주세요"
        ),
        7 to TutorialStepData(
            description =" 확인버튼을 눌러주세요"
        ),
        8 to TutorialStepData(
            description = "확인버튼을 눌러주세요"
        ),
        9 to TutorialStepData(
            description = "기다리시면 시작화면으로 돌아갑니다"
        )

    )

    var money: String by mutableStateOf("0")


    @Composable
    fun MainAct() {
        var toast: Toast? = null
        val context = LocalContext.current
        val vScroll = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(vScroll)
                .fillMaxSize()
                .background(backGround),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ActMainTitle(
                title = "은행"
            )
            ActMainContent {
                when(getCounter()){
                    0 -> SelectBankwork()//처음 시작화면
                    1 -> SelectBankwork()
                    2 -> SelectOutkind()
                    3 -> Kardcheck()
                    4 -> PrassPassword()
                    5 -> PrassPassword()// 확인버튼 출력부분
                    6 -> SelectMoney()
                    //7 ->
                }
            }

        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTrainMainTutorial() {
    val bankMain = BankMain(true, 3)
    bankMain.Layout {
        bankMain.MainAct()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrainMainReal() {
    val bankMain = BankMain(false)
    bankMain.Layout {
        bankMain.MainAct()
    }
}