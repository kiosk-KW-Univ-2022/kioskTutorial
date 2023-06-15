package com.example.kiosktutorial.Screen.Train

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.TutorialStepData

class TrainSelect(isTutorial: Boolean, val viewModel: TrainDataViewModel, step: Int = 1) :
    IKiosk(isTutorial, step) {

    override var isForceModifyingStep = true


    override var tutorialStepDataList: Map<Int, TutorialStepData> = mutableMapOf(
        0 to TutorialStepData(
            description = "이전 페이지 선택창에서 넘어왔습니다.",
            stateFunction = {
                viewModel.navController?.popBackStack()
            }
        ),
        1 to TutorialStepData(
            description = "열차를 선택합니다.\n목록에서 빨갛게 강조 표시된 버튼을 눌러 일반석/특석을 선택 해주세요.",
            stateFunction = {
                viewModel.trainSelectData.selectedTrain.value = Pair("",false)
            }

        ),
        2 to TutorialStepData(
            description="열차를 선택했습니다. 이제 열차 좌석을 고르겠습니다. 우측 하단의 좌석선택을 눌러주세요.",
// FIXME: applying this parameter, occurs error that can not move to next step
//          why occurs this happens, unknown.
//            description = "선택한 열차는 ${viewModel.trainSelectData.selectedTrain.value.first}번입니다.\n이제 열차 좌석을 고르겠습니다. 우측 하단의 좌석선택을 눌러주세요.",
            alignment = Alignment.TopCenter,
            stateFunction = {

                viewModel.trainSelectData.selectedTrain.value =Pair("364", false)

            }
        ),
        3 to TutorialStepData(
            description = "다음 버튼을 눌러 다음 작업으로 이동하세요.",
            stateFunction = {
                // FIXME: move to here from next activity of this action.
                if(it) moveNext()
                else forceModifyingStep(2)
            }
        ),
        4 to TutorialStepData(
            description = "예매 버튼을 눌러 다음으로 이동합니다.",
            alignment = Alignment.TopCenter,
            stateFunction = {}
        )
    )

    @Composable
    fun MainAct() {
        Column(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            TrainMenu()
            Box(
                modifier = Modifier
                    .weight(1f)
            ){
                TrainList()
            }
            with(viewModel.trainSelectData) {
                if (selectedTrain.value.first != "") {
                    PayingArea()
                }
            }
        }
    }

    fun moveNext(){
        forceModifyingStep(4)
        viewModel.trainSelectStep = getCounter()
        viewModel.navController?.navigate("${if(isTutorial()) "tutorial" else "real" }TrainSeatSelectionAct")
    }

    internal var toast: Toast? = null
    override val STEP_MAX = 300
}

@Preview(showBackground = true)
@Composable
fun PreviewTutorialTrainSelect() {
    val trainSelect = TrainSelect(true, TrainDataViewModel(1), 1)

    with(trainSelect) {
        Layout {
            MainAct()
        }
    }

}
