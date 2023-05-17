package com.example.kiosktutorial.Screen.Train

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import java.time.LocalDate

class TrainDataViewModel(var step:Int, val navController: NavHostController? = null) : ViewModel(){
    var trainSelectData by mutableStateOf(TrainSelectData())
    var isInit:Boolean

    init{
        trainSelectData = TrainSelectData()
        isInit = false
    }

    fun Initialize(){
        if(isInit) trainSelectData = TrainSelectData()
        isInit = false

    }


}

data class TrainSelectData(
    var subwayStart:String = "서울",
    var subwayEnd:String = "부산",
    var selectDay: LocalDate = LocalDate.now(),
    var selectHour: Int = 0,
    var personTicket:Map<String, MutableState<Int>> = mutableMapOf(
        "어른(만 13세 이상)" to mutableStateOf(0),
        "어린이(만 6 - 12세)" to mutableStateOf(0),
        "유아(만 6세 미만)" to mutableStateOf(0),
        "경로(만 65세 이상)" to mutableStateOf(0),
        "중증 장애인" to mutableStateOf(0),
        "경증 장애인" to mutableStateOf(0)
    )
)
