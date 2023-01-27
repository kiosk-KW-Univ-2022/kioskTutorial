package com.example.kiosktutorial.Screen.Kiosk

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*

private val personType = arrayOf("어린이", "성인", "경로")

@Composable
fun TrainCheckPage(
    navHostController: NavHostController,
    dpkg:dataPackage,
    onSetDisplay:(Int)->Unit
){
    Column(
        modifier = Modifier
            .background(Color(0xFFD3D3D3))
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff1490df))
                .padding(15.dp, 10.dp)

        ){
            Text(
                text="승차권 정보 확인",
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.Center)

            )
            Text(
                text="×",
                fontSize=30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clickable {
                        onSetDisplay(0)
                    }
                    .width(30.dp)
            )
        }

        val time = SimpleDateFormat("yyyy년 MM월 dd일").format(dpkg.startStation.ticketTime)
        val ticketTotal = dpkg.TicketStorage[personType[0]]!!.count + dpkg.TicketStorage[personType[1]]!!.count +
                dpkg.TicketStorage[personType[2]]!!.count

        Column(
            modifier = Modifier
                .background(Color(0xffffffff))
                .padding(20.dp)
                .fillMaxWidth()

        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "$time"
                )
                Text(
                    text = "${ticketTotal}매",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)

                )
            }
            Text(
                text = "${dpkg.startStation.stationName} → ${dpkg.endStation.stationName}",
                fontSize = 28.sp
            )

            var s:String = ""
            for(i:Int in 1..ticketTotal){
                s += "일반실 0호차 ${String.format("%02d", i)}, "
            }
            s = s.substring(0, s.length-2)

            Text(
                text = "$s",
                fontSize = 22.sp
            )

            val cal = Calendar.getInstance()
            cal.time = dpkg.startStation.ticketTime
            cal.add(Calendar.MINUTE, 10)
            val limitTime = SimpleDateFormat("yyyy년 MM월 dd일 HH:mm").format(cal.time)
            Text(
                text = "결제기한: ${limitTime}\n10분 내 미 결제시 승차권이 자동으로 취소됩니다",
                color = Color(0xffff7a6b),
                fontSize = 16.sp
            )
//            personType.forEach{
//                if(dpkg.TicketStorage[it]!!.count > 0){
//                    Text(
//                        text = "$it ${dpkg.TicketStorage[it]!!.count}매"
//                    )
//                }
//            }
        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .weight(1f)

        ){
            Text(
                text ="승차권을 발권받은 휴대전화에서만 확인할 수 있습니다\n학인 승차권 이용시에는 관련 신분증 또는 증명서를 소지해야 합니다",
                fontSize = 16.sp
            )
            Spacer(
                modifier = Modifier
                    .padding(0.dp, 10.dp)
                    .background(Color(0xFF9C9C9C))
                    .fillMaxWidth()
                    .height(1.dp)

            )
            Text(
                text ="본 발권 시스템은 실제 열차 예매가 아닌, 연습을 위한 시스템입니다.",
                fontSize = 16.sp

            )
        }

        val context = LocalContext.current
        var toast: Toast? = null
        Button(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .align(Alignment.CenterHorizontally),
            onClick={
                toast?.cancel()
                toast = Toast.makeText(context, "발권 성공", Toast.LENGTH_LONG)
                toast!!.show()
                navHostController.popBackStack()
            }
        ){
            Text(
                text ="발권하기"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTrainCheck(){
    TrainCheckPage( rememberNavController(),
        dataPackage(StationState("서울", Date()), StationState("용산", Date()),
            mutableMapOf("어린이" to TicketCount(0), "성인" to TicketCount(1),"경로" to TicketCount(0))
        ),
        {it}
    )
}