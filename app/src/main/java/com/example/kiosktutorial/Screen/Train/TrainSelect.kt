package com.example.kiosktutorial.Screen.Train

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.TutorialStepData

class TrainSelect(isTutorial: Boolean, val viewModel:TrainDataViewModel, step: Int = 1) : IKiosk(isTutorial, step) {
    override var tutorialStepDataList: Map<Int, TutorialStepData> = mutableMapOf(
        0 to TutorialStepData(
            description = "이전 페이지 선택창에서 넘어왔습니다.",
            stateFunction = {
                viewModel.navController?.popBackStack()
            }
        ),
        1 to TutorialStepData(
            description = "이제 좌석을 선택합니다.\n아래 목록에서 일반석/특석을 선택 해주세요."
        )
    )

    @Composable
    fun MainAct(){
        Column(

        ){
            TrainMenu()
            TrainList()
        }
    }

    internal var toast: Toast? = null

}

@Preview(showBackground = true)
@Composable
fun PreviewTutorialTrainSelect(){
    val trainSelect = TrainSelect(true, TrainDataViewModel(1), 1)

    with(trainSelect){
        Layout{
            MainAct()
        }
    }

}
