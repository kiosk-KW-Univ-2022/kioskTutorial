package com.example.kiosktutorial.Screen.Train

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.TutorialStepData

class TrainSeat(isTutorial:Boolean, viewModel:TrainDataViewModel, step:Int = 1): IKiosk(isTutorial, step) {
    override var tutorialStepDataList: Map<Int, TutorialStepData> = mapOf(
        1 to TutorialStepData(
            description = "테스트 메시지"
        )
    )

    @Composable
    fun MainAct(){
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Text(
                text = "test"
            )
        }
    }

}