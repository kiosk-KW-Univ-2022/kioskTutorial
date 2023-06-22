package com.example.kiosktutorial.Screen.Train

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.Screen
import com.example.kiosktutorial.Screen.TutorialStepData

class TrainTicketCheck(isTutorial:Boolean, val viewModel:TrainDataViewModel, step:Int = 1,
):
    IKiosk(isTutorial, step) {
    override var tutorialStepDataList: Map<Int, TutorialStepData> = mutableStateMapOf(
        1 to TutorialStepData(
            description = "티켓 정보를 확인한 후 결제버튼 누르세요"
        ),
        2 to TutorialStepData(
            description = "end",
            stateFunction = {
                moveNext()
            }

        )

    )

    internal fun moveNext(){
        viewModel.navController?.navigate("${Screen.PayWindow.route}/${Screen.Home.route}")

    }

    @Composable
    fun MainAct(){
        Column(
            modifier = Modifier
                .background(Color(0xFFD3D3D3))
                .fillMaxSize()
        ) {
            Title()
            InfoCheckArea()
            Column(
                modifier = Modifier
                    .weight(1f)
            ){
                WarnArea()

            }
            PayingArea()
            if(isTutorial()){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ){

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTrainTicketCheck(){
    var t = TrainTicketCheck(true, TrainDataViewModel(1),1)
    t.Layout{
        t.MainAct()
    }
}
