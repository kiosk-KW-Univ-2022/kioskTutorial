package com.example.kiosktutorial.Screen.Train

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.TutorialStepData
import java.time.LocalDate
import java.util.*

class TrainMain(isTutorial: Boolean, step: Int = 0) : IKiosk(isTutorial, step) {
    override var tutorialStepDataList: Map<Int, TutorialStepData> = mutableMapOf(
        0 to TutorialStepData(
            description = "고속 철도 예매 튜토리얼을 시작합니다\n시작을 하려면 화면을 눌러주세요"
        ),
        1 to TutorialStepData(
            description = "화면의 출발역 부분을 눌러주세요",
        ),
        2 to TutorialStepData(
            description = "아래 목록에서 출발역을 선택해주세요",
            alignment = Alignment.TopCenter
        ),
        3 to TutorialStepData(
            description = "화면의 도착역 부분을 눌러주세요",
        ),
        4 to TutorialStepData(
            description = "아래 목록에서 도착역을 선택해주세요",
            alignment = Alignment.TopCenter
        ),
        5 to TutorialStepData(
            description = "출발일 영역을 눌러주세요"
        ),
        6 to TutorialStepData(
            description = "출발할 날짜를 선택해주세요"
        ),
        7 to TutorialStepData(
            description ="""
                시간을 선택합니다.
                해당 시간 이후에 존재하는 열차로 조회할 수 있습니다.
                만일 8시로 선택하는 경우, 08:00시 이후 열차를 조회할 수 있습니다.
            """.trimIndent()
        ),
        8 to TutorialStepData(
            description = """
                승객 연력 및 좌석수를 결정합니다.
                강조된 영역을 눌러 승객 수를 결정할 수 있습니다.
            """.trimIndent()
        ),
        9 to TutorialStepData(
            description = """
                모든 연령의 티켓 수의 합이 9개 이상이 되면 더이상 추가할 수 없습니다.
                연습 중이니 하나만 추가 해보세요
            """.trimIndent(),
            alignment = Alignment.TopCenter
        ),
        10 to TutorialStepData(
            description = """
                하단의 열차 조회하기를 눌러 다음 단계로 넘어갑니다. 
            """.trimIndent(),
            alignment = Alignment.TopCenter
        )

    )

    var subwayStart: String by mutableStateOf("서울")
    var subwayEnd: String by mutableStateOf("용산")
    var isStartSubway:Boolean? by mutableStateOf(null)
    val correctStepHighlight = Modifier
        .border(2.dp, Color.Red)
    @delegate:RequiresApi(Build.VERSION_CODES.O)
    var selectedDay by mutableStateOf(LocalDate.now())
    var selectedHour by mutableStateOf(0)
    var personTicket = mutableMapOf<String, MutableState<Int>>(
        "어른(만 13세 이상)" to mutableStateOf(0),
        "어린이(만 6 - 12세)" to mutableStateOf(0),
        "유아(만 6세 미만)" to mutableStateOf(0),
        "경로(만 65세 이상)" to mutableStateOf(0),
        "중증 장애인" to mutableStateOf(0),
        "경증 장애인" to mutableStateOf(0),
    )
    


    @Composable
    fun MainAct() {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TitleArea()

                val vScroll = rememberScrollState()
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(vScroll)
                ){
                    SubwaySelectionArea()
                    dSpacer()
                    DaySelectArea()
                    dSpacer()
                    PersonCountArea()

                }
                searchTrainButton()
            }

            if(isTutorial())
                when(getCounter()){
                    0 -> StartView()            // showing what user selected
                }

            if(isStartSubway != null){
                // showing also step when 2, 4
                SubwayChoicePanel()
            }

        }

    }



}


@Preview(showBackground = true)
@Composable
fun PreviewTrainMainTutorial() {
    val trainMain = TrainMain(true, 5)
    trainMain.Layout {
        trainMain.MainAct()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrainMainReal() {
    val trainMain = TrainMain(false)
    trainMain.Layout {
        trainMain.MainAct()
    }
}
