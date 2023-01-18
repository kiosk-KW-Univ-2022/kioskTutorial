package com.example.kiosktutorial.Screen.Kiosk

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

var startTicketTime:Date = Date()
var endTicketTime:  Date = Date()

@OptIn(ExperimentalPagerApi::class)
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
        var startStation by remember { mutableStateOf("서울") }
        var endStation by remember { mutableStateOf("진부(오대산)") }
        var startStationState by remember{ mutableStateOf(StationState("", {s}))}
        val pagerState = rememberPagerState(0)
        Tabs(pagerState = pagerState)
        TabContent(
            pagerState = pagerState,
            startStation,
            { startStation = "서울u" },
            endStation,
            { endStation = "진부(오대산)u" })
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val pages = listOf("편도", "왕복")

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.background,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color(0xff6e95db)
            )
        }

    ) {
        pages.forEachIndexed { index, _ ->
            Tab(selected = pagerState.currentPage == index,
                text = {
                    Text(
                        text = pages[index],
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                })
        }
    }

}

@ExperimentalPagerApi
@Composable
fun TabContent(
    pagerState: PagerState,
    startStation: String,
    onStartStationSelection: (String) -> Unit,
    endStation: String,
    onEndStationSelection: (String) -> Unit
) {
    HorizontalPager(
        state = pagerState, count = 2,
        modifier = Modifier
            .fillMaxSize()
    ) { page ->
        when (page) {
            0 -> DirectionalTicket(
                startStation,
                onStartStationSelection,
                endStation,
                onEndStationSelection
            )
            1 -> BiDirectionalTicket(
                startStation,
                onStartStationSelection,
                endStation,
                onEndStationSelection
            )
        }
    }
}

@Composable
fun StationSelector(
    label: String,
    stationName: String,
    onStationSelection: (String) -> Unit,
    modifier: Modifier? = null
) {
    val mod = modifier ?: Modifier.fillMaxWidth(0.4f)
    Column(
        modifier = mod,
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
            text = stationName,
            fontSize = 30.sp,
            modifier = Modifier.clickable { onStationSelection(stationName) }
        )
    }
}

@Composable
fun StationBox(
    biDirectionMode: Boolean,
    startStation: String,
    onStartStationSelection: (String) -> Unit,
    endStation: String,
    onEndStationSelection: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        StationSelector("출발", startStation, onStartStationSelection);
        Column(
            modifier = Modifier.fillMaxWidth(0.2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🔄",
                fontSize = 20.sp,
            )
            Text(
                text = if (biDirectionMode) "⇋" else "→",
                fontSize = 25.sp
            )
        }
        StationSelector(
            "도착",
            endStation,
            onEndStationSelection,
            modifier = Modifier.fillMaxWidth(1f)
        )

    }
}

@Composable
fun DateSelectSelector() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .heightIn(20.dp, 40.dp)
    ) {
        val cal = Calendar.getInstance()
        for (i: Int in 1..14) {
            cal.time = Date()
            cal.add(Calendar.DATE, i)
            val format = SimpleDateFormat("MM월 dd일").format(cal.time)
            Box() {
                Text(
                    text = "$format",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(5.dp)
                        .background(Color(0xffDDDDDD))
                        .clickable{
                            startTicketTime = cal.time
                        }

                )
            }
        }
    }
}

@Composable
fun DateSelectBox(label: String, isStart: Boolean) {
    var selectorVisiblity by remember { mutableStateOf(false) }
    var time by remember{mutableStateOf(startTicketTime)}
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 20.sp
        )
        val format = SimpleDateFormat("yyyy년 MM월 dd일").format(time)
        val paintD = painterResource(R.drawable.expand_more);
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // TODO: 터치시 하단에 선택 영역 추가
                    selectorVisiblity = !selectorVisiblity;
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "$format",
                fontSize = 25.sp
            )
            if(!selectorVisiblity){
                Icon(
                    painter = paintD,
                    contentDescription = "$label 선택 영역 확장",
                    modifier = Modifier.height(20.dp),
                    tint = Color(0xff1793e0)
                )
            }
        }
        if (selectorVisiblity) {
            DateSelectSelector()

            Icon(
                painter = paintD,
                contentDescription = "$label 선택 영역 축소",
                modifier = Modifier.height(20.dp)
                    .rotate(90.0f),
                tint = Color(0xff1793e0)

            )
        }
    }
}

@Composable
fun DirectionalTicket(stationInfo:StationState) {
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxSize()
    ) {
        StationBox(false, stationInfo.stationName, onStartStationSelection, endStation, onEndStationSelection)
        Spacer(
            modifier = Modifier
                .padding(15.dp, 5.dp)
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.LightGray)
        )
        DateSelectBox("출발일", true)
    }

}

@Composable
fun BiDirectionalTicket(
    startStation: String,
    onStartStationSelection: (String) -> Unit,
    endStation: String,
    onEndStationSelection: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxSize()
    ) {
        StationBox(false, startStation, onStartStationSelection, endStation, onEndStationSelection)
        Spacer(
            modifier = Modifier
                .padding(15.dp, 5.dp)
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.LightGray)
        )
        DateSelectBox("가는 날", true)
        Spacer(
            modifier = Modifier
                .padding(15.dp, 5.dp)
                .fillMaxWidth()
                .height(2.dp)
                .background(Color.LightGray)
        )
        DateSelectBox("오는 날", true)

    }

}

data class StationState(
    val stationName:String,
    val onStationChanged: (String) -> Unit,
)

data class TicketInfo(

)

// light mode
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewKioskTrain() {
    KioskTrain(rememberNavController())
}