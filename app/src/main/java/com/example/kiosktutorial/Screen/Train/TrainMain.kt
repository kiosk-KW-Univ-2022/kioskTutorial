package com.example.kiosktutorial.Screen.Train

import android.util.Log
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

class TrainMain(
    isTutorial: Boolean,
    val viewModel: TrainDataViewModel
) : IKiosk(isTutorial, viewModel.step) {
    override var tutorialStepDataList: Map<Int, TutorialStepData> = mutableMapOf(
        0 to TutorialStepData(
            description = "고속 철도 예매 튜토리얼을 시작합니다\n시작을 하려면 화면을 눌러주세요",
            stateFunction = {
                subwayStart = "서울"; subwayEnd = "부산"; isStartSubway = null
                selectedDay = LocalDate.now(); selectedHour = 0
                personTicket.forEach {
                    personTicket[it.key]!!.value = 0
                }
            }
        ),
        1 to TutorialStepData(
            description = "화면의 출발역 부분을 눌러주세요",
            stateFunction = {
                isStartSubway = null
            }
        ),
        2 to TutorialStepData(
            description = "아래 목록에서 출발역을 선택해주세요",
            alignment = Alignment.TopCenter,
            stateFunction = {
                isStartSubway = true
                subwayStart = "서울"
            }
        ),
        3 to TutorialStepData(
            description = "화면의 도착역 부분을 눌러주세요",
            stateFunction = {
                isStartSubway = null
                subwayStart = "용산"
            }
        ),
        4 to TutorialStepData(
            description = "아래 목록에서 도착역을 선택해주세요",
            alignment = Alignment.TopCenter,
            stateFunction = {
                isStartSubway = false
                subwayEnd = "부산"
            }
        ),
        5 to TutorialStepData(
            description = "출발일 영역을 눌러주세요",
            stateFunction = {
                isStartSubway = null
                subwayEnd = "창원중앙"
                daySelectVisibility = false
            }
        ),
        6 to TutorialStepData(
            description = "출발할 날짜를 선택해주세요",
            stateFunction = {
                daySelectVisibility = true
                selectedDay = LocalDate.now()
            }
        ),
        7 to TutorialStepData(
            description = """
                시간을 선택합니다.
                해당 시간 이후에 존재하는 열차로 조회할 수 있습니다.
                만일 8시로 선택하는 경우, 08:00시 이후 열차를 조회할 수 있습니다.
            """.trimIndent(),
            stateFunction = {
                selectedDay = LocalDate.now().plusDays(2)
                selectedHour = 0
            }
        ),
        8 to TutorialStepData(
            description = """
                승객 연력 및 좌석수를 결정합니다.
                강조된 영역을 눌러 승객 수를 결정할 수 있습니다.
            """.trimIndent(),
            stateFunction = {
                selectedHour = 6
                personTicketCountVisibility = false
            }
        ),
        9 to TutorialStepData(
            description = """
                모든 연령의 티켓 수의 합이 9개 이상이 되면 더이상 추가할 수 없습니다.
                연습 중이니 하나만 추가 해보세요
            """.trimIndent(),
            alignment = Alignment.TopCenter,
            stateFunction = {
                personTicketCountVisibility = true
                personTicket.forEach {
                    personTicket[it.key]!!.value = 0
                }
            }
        ),
        10 to TutorialStepData(
            description = """
                하단의 열차 조회하기를 눌러 다음 단계로 넘어갑니다. 
            """.trimIndent(),
            alignment = Alignment.TopCenter,
            stateFunction = {
                personTicket[personTicket.keys.first()]!!.value = 1
            }
        ),
        11 to TutorialStepData(
            description = "",
            stateFunction = {
                moveNext()
            }
        )

    )

    internal fun moveNext() {
        with(viewModel){
            trainSelectData.personTicket = personTicket
            trainSelectData.selectDay = selectedDay
            trainSelectData.selectHour = selectedHour
            trainSelectData.subwayStart = subwayStart
            trainSelectData.subwayEnd = subwayEnd
        }
        viewModel.step = getCounter()
        viewModel.navController?.navigate("${if(isTutorial()) "tutorial" else "real" }TrainSelectionAct")
    }

    var subwayStart by mutableStateOf("")
    var subwayEnd by mutableStateOf("")
    var isStartSubway: Boolean? by mutableStateOf(null)
    val correctStepHighlight = Modifier
        .border(2.dp, Color.Red)
    var selectedDay: LocalDate by mutableStateOf(LocalDate.now())
    var selectedHour by mutableStateOf(0)
    var personTicket: Map<String, MutableState<Int>>

    var daySelectVisibility by mutableStateOf(false)
    var personTicketCountVisibility by mutableStateOf(false)

    init {

        subwayStart = viewModel.trainSelectData.subwayStart
        subwayEnd = viewModel.trainSelectData.subwayEnd
        selectedDay = viewModel.trainSelectData.selectDay
        selectedHour = viewModel.trainSelectData.selectHour
        personTicket = viewModel.trainSelectData.personTicket
    }

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
                ) {
                    SubwaySelectionArea()
                    Spacer()
                    DaySelectArea()
                    Spacer()
                    PersonCountArea()

                }
                SearchTrainButton()
            }

            if (isTutorial())
                when (getCounter()) {
                    0 -> StartView()            // showing what user selected
                }

            if (isStartSubway != null) {
                // showing also step when 2, 4
                SubwayChoicePanel()
            }

        }

    }


}


@Preview(showBackground = true)
@Composable
fun PreviewTrainMainTutorial() {

    val trainMain = TrainMain(true, TrainDataViewModel(0))
    trainMain.Layout {
        trainMain.MainAct()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrainMainReal() {
    val trainMain = TrainMain(false, TrainDataViewModel(0))
    trainMain.Layout {
        trainMain.MainAct()
    }
}
