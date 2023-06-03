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
import java.time.LocalDateTime
import java.time.LocalTime

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

data class TrainInfo(
    var trainNumber:String,
    var start:LocalTime,
    var end: LocalTime
)

// for TrainSelectLayout TrainList
val trainItemList = listOf(
    TrainInfo("173",
        LocalTime.of(0,30),
        LocalTime.of(1,30),
    ),
    TrainInfo("174",
        LocalTime.of(6,30),
        LocalTime.of(7,32)
    ),
    TrainInfo("183",
        LocalTime.of(7,22),
        LocalTime.of(8,36)
    ),
    TrainInfo("273",
        LocalTime.of(8,0),
        LocalTime.of(9,15)
    ),
    TrainInfo("364",
        LocalTime.of(9,46),
        LocalTime.of(11,26)
    ),
    TrainInfo("385",
        LocalTime.of(10,15),
        LocalTime.of(11,46)
    ),
    TrainInfo("415",
        LocalTime.of(10,58),
        LocalTime.of(12,15)
    ),
    TrainInfo("423",
        LocalTime.of(11,43),
        LocalTime.of(12,53)
    ),
    TrainInfo("526",
        LocalTime.of(12,15),
        LocalTime.of(13,43)
    ),
    TrainInfo("543",
        LocalTime.of(13,15),
        LocalTime.of(14,43)
    ),
    TrainInfo("562",
        LocalTime.of(13,45),
        LocalTime.of(15,10)
    ),
    TrainInfo("599",
        LocalTime.of(14,23),
        LocalTime.of(15,53)
    ),
    TrainInfo("623",
        LocalTime.of(15,0),
        LocalTime.of(16,32)
    ),
    TrainInfo("682",
        LocalTime.of(15,32),
        LocalTime.of(16,58)
    ),
    TrainInfo("723",
        LocalTime.of(16,4),
        LocalTime.of(17,32)
    ),
    TrainInfo("742",
        LocalTime.of(16,43),
        LocalTime.of(17,49),
    ),
    TrainInfo("823",
        LocalTime.of(17,32),
        LocalTime.of(18,26)
    ),
    TrainInfo("842",
        LocalTime.of(17,54),
        LocalTime.of(18,47),
    ),
    TrainInfo("844",
        LocalTime.of(18,43),
        LocalTime.of(20,2)
    ),
    TrainInfo("869",
        LocalTime.of(19, 27),
        LocalTime.of(20,37)
    ),
    TrainInfo("938",
        LocalTime.of(20,43),
        LocalTime.of(22,3)
    ),
    TrainInfo("999",
        LocalTime.of(21,25),
        LocalTime.of(22,40)
    ),
    TrainInfo("1038",
        LocalTime.of(22,8),
        LocalTime.of(23,40)
    ),
)