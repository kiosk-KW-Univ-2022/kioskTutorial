package com.example.kiosktutorial.Screen.Train

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.TutorialStepData

class TrainSeat(isTutorial:Boolean, val viewModel:TrainDataViewModel, step:Int = 1): IKiosk(isTutorial, step) {
    override var tutorialStepDataList: Map<Int, TutorialStepData> = mapOf(
        0 to TutorialStepData(
            description = "이전 페이지로 이동합니다.",
            stateFunction = {
                viewModel.navController?.popBackStack()
            }
        ),
        1 to TutorialStepData(
            description = "상단 빨간 테두리 버튼을 눌러 열차호수를 선택할 수 있습니다. 상단 빨간 테두리 버튼을 누르세요.",
            stateFunction = {
                selectedTrainSection = 1
            }
        ),
        2 to TutorialStepData(
            description = "목록에서 승차할 열차 호수를 선택해주세요.",
            stateFunction = {
                if(it){
                    if(selectedTrainSection == 1){
                        selectedTrainSection = 7
                        forceModifyingStep(3)
                    }
                }else{
                    forceModifyingStep(1)
                    selectedTrainSection = 1
                }
            }
        ),
        3 to TutorialStepData(
            description = "회색 좌석은 다른 사람이 예약한 좌석입니다.\n유색 좌석을 선택해서 티켓 수만큼 골라주세요.",
            stateFunction = {
                selectedTrainSection = 7
                comboboxExpanded = false
                selectedSeat.clear()
            },
            alignment = Alignment.TopCenter
        ),
        4 to TutorialStepData(
            description = "선택 완료 버튼을 누르고 이동하세요",
            alignment = Alignment.TopCenter,
            stateFunction = {
                selectedSeat.add("1A")
                moveNext()
            }
        )
    )

    internal fun moveNext(){
        viewModel.trainSection = selectedTrainSection
        viewModel.trainSeatList = selectedSeat;
        viewModel.navController?.popBackStack();
    }

    internal val seatColor = arrayOf(
        Color(0xfface1dd),
        Color(0xFFDBFFB6)
    )

    internal val selectedSeat = mutableStateListOf<String>()
    internal fun SelectSeatXor(str:String){
        var count = 0;
        viewModel.trainSelectData.personTicket.forEach{
            count += it.value.value
        }

        if(!selectedSeat.contains(str)) {
            if(selectedSeat.count() < count) selectedSeat.add(str)
            else if(count == 1) {
                selectedSeat.clear()
                selectedSeat.add(str)
            }
        }
        else selectedSeat.remove(str)
    }
    var selectedTrainSection by mutableStateOf(1)
    var comboboxExpanded by mutableStateOf(false)
    private val restSectionByTrainSection = arrayOf(
        20,20,20,20,20,20,20,20
    )
    val sectionCount = restSectionByTrainSection.size
    override var isForceModifyingStep = true
    fun getRestSeat(trainSectionInt:Int):Int{
        if(0 <= trainSectionInt && trainSectionInt <= restSectionByTrainSection.size)
            return restSectionByTrainSection[trainSectionInt]

        Log.d("TrainSeat", "trainSectionInt:${trainSectionInt}")
        return -1
    }


    @Composable
    fun MainAct(){
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ){
                Title()
                TrainSectionSelector()
                SeatSelectArea()
            }
            Box(
                modifier  =Modifier
                    .fillMaxWidth()
                    .background(Color(0xD2426464))
            ){
                SelectViewArea()
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTutorialTrainSeat(){
    val trainSeat = TrainSeat(true,TrainDataViewModel(1), 1)
    trainSeat.Layout{
        trainSeat.MainAct()
    }

}