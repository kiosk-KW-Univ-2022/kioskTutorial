package com.example.kiosktutorial.Screen.Train

import android.graphics.Paint.Align
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiosktutorial.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TrainMain.StartView() {
    Box(
        modifier = Modifier
            .setMode(
                0,
                Modifier
                    .alpha(1f)
                    .background(Color(0xFFffe65a))
                    .fillMaxSize()
            ) {},
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = "KTX 열차 예매",
            fontSize = with(LocalDensity.current) { 30.dp.toSp() }
        )
    }
}

@Composable
fun TrainMain.TitleArea() {
    Box(
        modifier = Modifier
            .background(Color(0xff_6e95db))
            .fillMaxWidth()
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "승차권 예매",
            fontSize = 20.sp
        )
    }
}

@Composable
fun TrainMain.SubwaySelectionArea() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(5.dp, 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            SelectSubwayButton(
                isStart = true,
                setMode = Modifier
                    .setMode(
                        1,
                        Modifier,
                        correctStepHighlight
                    ) {
                        isStartSubway = true
                    }
            )
        }
        Text(
            modifier = Modifier
                .weight(0.2f),
            text = "→",
            fontSize = with(LocalDensity.current) { 20.dp.toSp() },
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            SelectSubwayButton(
                isStart = false,
                setMode = Modifier
                    .setMode(
                        3,
                        Modifier,
                        correctStepHighlight
                    ) {
                        isStartSubway = false
                    }
            )
        }

    }

}

@Composable
fun TrainMain.SelectSubwayButton(
    isStart: Boolean,
    setMode: Modifier // Modifier.setMode setting
) {
    var title: String = if (isStart) "출발" else "도착"
    var subwayName = if (isStart) subwayStart else subwayEnd
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .composed { setMode }
    ) {
        val mod = Modifier
            .fillMaxWidth()
        Text(
            modifier = mod,
            text = title,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = mod,
            text = subwayName,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun TrainMain.SubwayChoicePanel() {
    val stations = arrayOf(
        "서울", "용산", "광명", "영등포", "수원", "천안아산", "천안", "오송", "조치원", "대전",
        "서대전", "김천구미", "구미", "동대구", "대구", "울산(통도사)", "포항", "경산", "밀양", "부산",
        "신주", "구포", "창원중앙", "평창", "진주(오대산)", "강릉", "익산", "전주", "광주송정", "목포",
        "순천", "여수EXPO", "동해", "정동진", "안동", "서원주", "원주", "마산"
    )

    Column(
        modifier = Modifier
            .padding(top = 140.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    topEnd = 15.dp
                )
            )
    ) {
        Box(
            modifier = Modifier
                .setMode(-1) {
                    isStartSubway = null
                }
                .background(Color(0xff1a9ce2))
                .padding(4.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            dropdownArrow(
                color = Color.White,
                upDirection = false
            )
        }

        val function: (String) -> Unit = {
            if (isStartSubway != null) {
                if (isStartSubway == true) subwayStart = it
                else subwayEnd = it
            }
            isStartSubway = null
        }
        val vScroll = rememberScrollState()
        Column(
            modifier = Modifier
                .background(Color.White)
                .verticalScroll(vScroll)
        ) {
            for (i in 0..(stations.size / 2)) {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (k in 0..1)
                        if (i * 2 + k < stations.size) {
                            Text(
                                text = stations[i * 2 + k],
                                modifier = Modifier
                                    .border(1.dp, Color.Black)
                                    .weight(1f)
                                    .padding(10.dp)
                                    .setMode(
                                        2
                                    ) {
                                        function(stations[i * 2 + k])
                                    }
                                    .setMode(
                                        4
                                    ) {
                                        function(stations[i * 2 + k])
                                    },

                                fontSize = with(LocalDensity.current) { 20.dp.toSp() }
                            )
                        }
                }
            }
        }

    }

}

@Composable
fun TrainMain.DaySelectArea() {
    var selectVisibility = remember { mutableStateOf(false) }
    Column() {
        Column(
            modifier = Modifier
                .setMode(
                    5,
                    defaultModifier = Modifier
                        .background(
                            if (selectVisibility?.value) Color(0xFFCFE8FF) else Color.White
                        )
                        .padding(5.dp),
                    additionalModifier = correctStepHighlight

                ) {
                    selectVisibility.value = !selectVisibility.value
                }

        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "출발일",
                    color = Color(0xFF457bd9),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }

            Box(
                contentAlignment = Alignment.Center
            ) {
                val format = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")
                val dateString = selectedDay.format(format)
                val dayOfWeekString: String = when (selectedDay.dayOfWeek) {
                    DayOfWeek.SUNDAY -> "일"
                    DayOfWeek.MONDAY -> "월"
                    DayOfWeek.TUESDAY -> "화"
                    DayOfWeek.WEDNESDAY -> "수"
                    DayOfWeek.THURSDAY -> "목"
                    DayOfWeek.FRIDAY -> "금"
                    DayOfWeek.SATURDAY -> "토"
                    else -> throw IllegalStateException("Invalid day of week")
                }
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$dateString ($dayOfWeekString) ${String.format("%02d:00", selectedHour)}",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                dropdownArrow(
                    color = Color(0xFF457bd9),
                    upDirection = selectVisibility.value

                )
            }
        }

        if (selectVisibility.value)
            Column(
                modifier = Modifier
            ) {
                // day selection area
                val hDayScroll = rememberScrollState()
                Row(
                    modifier = Modifier
                        .setMode(
                            6,
                            defaultModifier = Modifier
                                .horizontalScroll(hDayScroll, getCounter() == 6),
                            additionalModifier = correctStepHighlight
                        ){}
                ) {
                    var date = LocalDate.now()
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )
                    DaySelectButton(
                        date,
                        date.dayOfWeek,
                        "오늘"
                    )
                    for(i in 0..31){
                        date = date.plusDays(1)
                        DaySelectButton(
                            date,
                            date.dayOfWeek
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(10.dp)
                    )

                }

                // time selection area
                val hTimeScroll = rememberScrollState()
                Row(
                    modifier = Modifier
                        .setMode(
                            7,
                            defaultModifier = Modifier
                                .padding(0.dp, 15.dp)
                                .background(Color.LightGray),
                            additionalModifier = correctStepHighlight
                        ) {}
                        .horizontalScroll(hTimeScroll, getCounter() == 7)
                ){
                    for(i in 0..23){
                        HourSelectButton(i)
                    }
                }
            }

    }
}

@Composable
fun TrainMain.DaySelectButton(
    day: LocalDate?,
    dayOfWeek: DayOfWeek,
    customText: String? = null,
    modifier:Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .width(50.dp)
            .setMode(
                6
            ) {
                selectedDay = day
                selectedHour = 0
            },
        verticalArrangement = Arrangement.Center
    ) {
        val dayOfWeekString: String = when (dayOfWeek) {
            DayOfWeek.SUNDAY -> "일"
            DayOfWeek.MONDAY -> "월"
            DayOfWeek.TUESDAY -> "화"
            DayOfWeek.WEDNESDAY -> "수"
            DayOfWeek.THURSDAY -> "목"
            DayOfWeek.FRIDAY -> "금"
            DayOfWeek.SATURDAY -> "토"
            else -> throw IllegalStateException("Invalid day of week")
        }
        val dayColor = when (dayOfWeek) {
            DayOfWeek.SUNDAY -> Color.Red
            DayOfWeek.SATURDAY -> Color.Blue
            else -> Color.Black
        }
        // day string area
        Box(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = customText ?: dayOfWeekString,
                color = dayColor
            )
        }
        Box(
            modifier = Modifier
                .background(
                    if ((day?.dayOfMonth ?: 0) == (selectedDay.dayOfMonth ?: 0)) Color(
                        0xFF457bd9
                    ) else Color.White
                )
                .height(50.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val d = day?.dayOfMonth ?: 0
            Text(
                text = "$d",
                color = if((day?.dayOfMonth ?: 0) == (selectedDay.dayOfMonth ?: 0))Color.White else dayColor
            )
        }

    }
}

@Composable
fun TrainMain.HourSelectButton(hour:Int){
    var backgroundColor:Color
    var color:Color
    if(hour == selectedHour){
        backgroundColor = Color(0xff4799b9)
        color = Color.White
    }else{
        backgroundColor = Color(0x00FFFFFF)
        color = Color.Black
    }
    Box(
        modifier = Modifier
            .setMode(
                7,
                Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .background(backgroundColor)
            ){
                selectedHour = hour
            },
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text= String.format("%2d", hour),
                color = color,
                fontSize = 20.sp
            )
            Text(
                text = "시",
                color = color,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun TrainMain.PersonCountArea() {
    val detailVisibility = remember{mutableStateOf(false)}
    Column(){
        var count = 0

        Column(
            modifier = Modifier
                .setMode(
                    8,
                    defaultModifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (detailVisibility.value) Color(0xFFCFE8FF) else Color.White
                        ),
                    additionalModifier = correctStepHighlight
                ){
                    detailVisibility.value = !detailVisibility.value
                }
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "승객 연력 및 좌석수",
                color = Color(0xFF457bd9)
            )

            personTicket.forEach{
                count += it.value.value
            }

            val str = String.format("총 %d명", count)
            Text(
                text = str,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            dropdownArrow(
                color = Color(0xFF457bd9),
                upDirection = detailVisibility.value
            )
        }

        // detail area
        if(detailVisibility.value)
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
            ){
                Text(
                    text = "최소 1명 - 최대 9명",
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                )

                personTicket.forEach(){
                    personTicketNumeric(
                        it.key, count
                    )
                }

            }
    }

}
@Composable
fun TrainMain.personTicketNumeric(
    str:String,
    ttlCount:Int
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = str
        )
        Row(){
            val enableMinus = 0 < personTicket[str]!!.value
            val enablePlus = ttlCount < 9
            Text(
                text = "-",
                color = Color(0xFF555555),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier
                    .setMode(
                        9,
                        defaultModifier = Modifier
                            .width(50.dp)
                            .background(if(enableMinus) Color(0xFFFFFFFF) else Color(0xFF888888))
                            .border(1.dp, Color(0xFF888888)),
                    ){
                        if(enableMinus && 0 < personTicket[str]!!.value)
                            personTicket[str]!!.value = personTicket[str]!!.value - 1
                    }
            )

            Box(
                modifier = Modifier
                    .width(50.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "${personTicket[str]!!.value}",
                    modifier = Modifier
                )
            }

            Text(
                text = "+",
                color = Color(0xFF555555),
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier
                    .setMode(
                        9,
                        defaultModifier = Modifier
                            .width(50.dp)
                            .background(if(enablePlus) Color(0xFFFFFFFF) else Color(0xFF888888))
                            .border(1.dp, Color(0xFF888888)),
                        additionalModifier = correctStepHighlight
                    ){
                        if(enablePlus)
                            personTicket[str]!!.value = personTicket[str]!!.value + 1
                    }
            )

        }
    }
}


@Composable
fun TrainMain.searchTrainButton(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ){
        Box(
            modifier = Modifier
                .border(1.dp, Color.LightGray)
                .fillMaxHeight()
                .weight(1f)
                .background(Color(0xFFACCAFF)),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "간편구매 등록",
                color = Color(0xFF233D5A),
            )
        }
        Box(
            modifier = Modifier
                .setMode(
                    10,
                    defaultModifier = Modifier
                        .border(1.dp, Color.LightGray)
                        .fillMaxHeight()
                        .weight(1f)
                        .background(Color(0xFFACCAFF)),
                    additionalModifier = correctStepHighlight
                ){
                    // TODO("다음 act 이동 필요")
                },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "열차 조회하기",
                color = Color(0xFF233D5A),
            )
        }

    }
}

@Composable
fun TrainMain.dropdownArrow(
    color: Color,
    modifier: Modifier = Modifier,
    upDirection: Boolean = false
) {
    val icon = painterResource(R.drawable.expand_more)
    Box(
        modifier =Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .composed { modifier }
                .height(20.dp)
                .width(40.dp)
                .rotate(if (upDirection) 180f else 0f),
            tint = color
        )
    }
}

@Composable
fun TrainMain.dSpacer() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .padding(15.dp, 5.dp)
                .fillMaxWidth(0.98f)
                .height(2.dp)
                .background(Color.LightGray)
        )
    }
}
