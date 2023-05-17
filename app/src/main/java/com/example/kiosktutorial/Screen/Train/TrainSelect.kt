package com.example.kiosktutorial.Screen.Train

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.TutorialStepData
import java.time.LocalDate

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
        Box(
            modifier = Modifier
                .background(Color.Cyan)
        ){
            var personTicketCount = 0
            viewModel.trainSelectData.personTicket.forEach{
//                Log.d("personTicket", "${it.key}:${it.value.value}")
                personTicketCount += it.value.value
            }
            Text(
                text = "${isTutorial()}\n${personTicketCount}"
            )
        }
    }


}
