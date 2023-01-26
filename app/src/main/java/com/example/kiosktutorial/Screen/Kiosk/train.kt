package com.example.kiosktutorial.Screen.Kiosk

import android.content.res.Configuration
import android.os.Parcelable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun KioskTrain(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6E95DB))
                .padding(10.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(text = "승차권 예매", fontSize = 18.sp)
        }
        var startStation by remember { mutableStateOf(StationState("서울", Date())) }
        var endStation by remember { mutableStateOf(StationState("용산", Date())) }
        var displayMode by remember { mutableStateOf(0) }
        val ticketMap = remember {
            mutableMapOf(
                "어린이" to TicketCount(0),
                "성인" to TicketCount(0),
                "경로" to TicketCount(0)
            )
        }
//        var ticketCount by remember{ mutableStateOf(TicketStorage(TicketCount(0), TicketCount(0), TicketCount(0)))}

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DirectionalTicket(
                startStation,
                endStation,
                ticketMap,
                { mode: Int -> displayMode = mode })
            if (displayMode == 1)
                StationSelectDialog(startStation, displayMode, { mode: Int -> displayMode = mode })
            else if (displayMode == 2)
                StationSelectDialog(endStation, displayMode, { mode: Int -> displayMode = mode })

        }

    }
}

@Composable
fun StationSelectDialog(station: StationState, displayMode: Int, onSetDisplay: (Int) -> Unit) {
    val stations = arrayOf(
        "서울", "용산", "광명", "영등포", "수원", "천안아산", "천안", "오송", "조치원", "대전",
        "서대전", "김천구미", "구미", "동대구", "대구", "울산(통도사)", "포항", "경산", "밀양", "부산",
        "신주", "구포", "창원중앙", "평창", "진주(오대산)", "강릉", "익산", "전주", "광주송정", "목포",
        "순천", "여수EXPO", "동해", "정동진", "안동", "서원주", "원주", "마산"
    )

    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxSize()
            .background(Color(0xffffffff))
            .clip(RoundedCornerShape(10.dp))
    ) {
        var title = ""
        when (displayMode) {
            1 -> title = "출발지"
            2 -> title = "도착지"
        }
        Text(
            text = "$title:${station.stationName}",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff27a6e9))
                .padding(5.dp)
                .align(Alignment.CenterHorizontally)
                .clickable(enabled = false) {},
            fontSize = 30.sp
        )
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            for (i: Int in 0..(stations.size / 2)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    for (k: Int in 0..1) {
                        if (i * 2 + k < stations.size) {
                            Text(
                                text = stations[i * 2 + k],
                                modifier = Modifier
                                    .clickable {
                                        if (displayMode == 1) {
                                            station.stationName = stations[i * 2 + k]
                                        } else
                                            station.stationName = stations[i * 2 + k]
                                        onSetDisplay(0)
                                    }
                                    .padding(10.dp)
                                    .fillMaxWidth(if (k == 0) 0.5f else 1f),
                                fontSize = 28.sp

                            )
                        }

                    }
                }
            }

        }
    }
}

@Composable
fun StationSelector(
    label: String,
    station: StationState,
    mode: Int,
    onSetDisplay: (Int) -> Unit,
    modifier: Modifier? = null
) {
    val mod = modifier ?: Modifier.fillMaxWidth(0.4f)
    Column(
        modifier = Modifier
            .clickable {
                onSetDisplay(mode)
            }
            .then(mod),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(bottom = 5.dp)
        )
        Text(
            text = "${station.stationName}",
            fontSize = 30.sp,
        )
    }
}

@Composable
fun StationBox(
    startStation: StationState,
    endStation: StationState,
    onSetDisplay: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        StationSelector("출발", startStation, 1, onSetDisplay);
        Column(
            modifier = Modifier.fillMaxWidth(0.2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "→",
                fontSize = 25.sp
            )
        }
        StationSelector(
            "도착",
            endStation,
            2,
            onSetDisplay,
            modifier = Modifier.fillMaxWidth(1f)
        )

    }
}

@Composable
fun makeDateSelectBtn(
    station: StationState,
    showMonth: Boolean,
    date: Date,
    onSetDate: (StationState, Date) -> Unit
) {
    val format = SimpleDateFormat("${if (showMonth) "M월 " else ""}d일").format(date)
    Box(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "$format",
            fontSize = 25.sp,
            modifier = Modifier
                .background(Color(0xffDDDDDD))
                .clip(RoundedCornerShape(4.dp))
                .padding(10.dp)
                .clickable {
                    onSetDate(station, date)
                }

        )
    }

}

@Composable
fun DateSelectSelector(station: StationState, onSetDate: (StationState, Date) -> Unit) {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .heightIn(20.dp)
            .horizontalScroll(scrollState)
    ) {
        val cal = Calendar.getInstance()
        var temp: Date = cal.time
        for (i: Int in 0..14) {
            cal.time = Date()
            cal.add(Calendar.DATE, i)
            Box() {
                makeDateSelectBtn(
                    station = station,
                    temp.month != cal.time.month,
                    date = cal.time,
                    onSetDate
                )
            }
            temp = cal.time
        }
    }
}

@Composable
fun DateSelectBox(label: String, station: StationState) {
    var selectorVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val format = SimpleDateFormat("yyyy년 MM월 dd일").format(station.ticketTime)
        val paintD = painterResource(R.drawable.expand_more);
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    selectorVisible = !selectorVisible;
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                fontSize = 20.sp
            )

            Text(
                text = "$format",
                fontSize = 25.sp
            )
            Icon(
                painter = paintD,
                contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
                    .rotate(if (selectorVisible) 180.0f else 0f),
                tint = Color(0xff1793e0)
            )
        }
        if (selectorVisible) {
            DateSelectSelector(station, { s: StationState, d: Date ->
                s.ticketTime = d
                selectorVisible = false
            })

        }
    }
}

@Composable
fun dSpacer() {
    Spacer(
        modifier = Modifier
            .padding(15.dp, 5.dp)
            .fillMaxWidth(0.98f)
            .height(2.dp)
            .background(Color.LightGray)
    )
}

@Composable
fun makeTicketCount(ticket: Int) {
    Text(
        text = "${ticket}",
        fontSize = 30.sp,
        modifier = Modifier
            .padding(10.dp, 0.dp)

    )
}


@Composable
fun TicketSelector(
    label: String, ticket: TicketCount,
    onIncrease: (TicketCount) -> Unit, onDecrease: (TicketCount) -> Unit,
    onUpdateTotal:()->Unit
) {
    var c by remember { mutableStateOf(ticket.count) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth(0.4f),
            fontSize = 25.sp
        )
        Button(
            onClick = {
                onDecrease(ticket)
                onUpdateTotal()
                c = ticket.count
            }
        ) {
            Text(
                text = "▼"
            )
        }
        makeTicketCount(c)
        Button(
            onClick = {
                onIncrease(ticket)
                onUpdateTotal()
                c = ticket.count
            }
        ) {
            Text(
                text = "▲"
            )
        }
    }
}

@Composable
fun TicketBox(
    ticketStorage: MutableMap<String, TicketCount>,
    onIncrease: (TicketCount) -> Unit, onDecrease: (TicketCount) -> Unit
) {
    var countVisible by remember { mutableStateOf(false) }
    val personType = arrayOf("어린이", "성인", "경로")
    var total by remember{ mutableStateOf(ticketStorage[personType[0]]!!.count +
            ticketStorage[personType[1]]!!.count + ticketStorage[personType[2]]!!.count) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 5.dp)

    ) {

        Column(
            modifier = Modifier
                .clickable {
                    countVisible = !countVisible
                }
                .fillMaxWidth()
        ) {

            Text(
                text = "발권 티켓 수: ${total}",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)

            )

            val paintD = painterResource(id = R.drawable.expand_more)

            Icon(
                painter = paintD,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .height(20.dp)
                    .rotate(if (countVisible) 180.0f else 0f)
                    .align(Alignment.CenterHorizontally),
                tint = Color(0xff1793e0)

            )
        }

        if (countVisible)
            personType.forEach {
                TicketSelector(label = it, ticketStorage[it]!!, onIncrease, onDecrease,
                    {
                        total = ticketStorage[personType[0]]!!.count +
                                ticketStorage[personType[1]]!!.count + ticketStorage[personType[2]]!!.count
                    })
            }

    }
}

@Composable
fun DirectionalTicket(
    startStation: StationState,
    endStation: StationState,
    ticketStorage: MutableMap<String, TicketCount>,
    onSetDisplay: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxSize()
    ) {
        StationBox(startStation, endStation, onSetDisplay)
        dSpacer()
        DateSelectBox("출발일", startStation)
        dSpacer()
        TicketBox(ticketStorage,
            { tc: TicketCount ->
                if (tc.count < 9) tc.count++
            },
            { tc: TicketCount ->
                if (tc.count > 0) tc.count--
            }
        )

    }

}

data class StationState(
    var stationName: String,
    var ticketTime: Date
)

@Parcelize
data class TicketStorage(
    var child: TicketCount,
    var adult: TicketCount,
    var oldMan: TicketCount
) : Parcelable

@Parcelize
data class TicketCount(
    var count: Int
) : Parcelable

// light mode
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewKioskTrain() {
    KioskTrain(rememberNavController())
}