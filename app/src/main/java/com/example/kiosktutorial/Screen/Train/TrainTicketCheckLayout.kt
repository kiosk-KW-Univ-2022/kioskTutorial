package com.example.kiosktutorial.Screen.Train

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiosktutorial.Screen.Screen
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TrainTicketCheck.Title() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0C3C60))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "승차권 정보 확인",
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

@Composable
fun TrainTicketCheck.InfoCheckArea() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Row() {
            Text(
                text = viewModel.trainSelectData.selectDay
                    .format(
                        DateTimeFormatter
                            .ofPattern(
                                "yyyy년 MM월 dd일 (${
                                    viewModel.trainSelectData.selectDay.getDayOfWeekString()
                                })"
                            )
                    ),
                modifier = Modifier
                    .weight(1f)
            )
            var count = 0;
            viewModel.trainSelectData.personTicket.forEach {
                count += it.value.value
            }
            Text(
                text = "${count}매"
            )
        }
        with(viewModel.trainSelectData) {
            var trainInfo: TrainInfo = TrainInfo("", LocalTime.of(1, 10), LocalTime.of(1, 10));
            trainItemList.forEach {
                if (selectedTrain.value.first == it.trainNumber) trainInfo = it
            }

            Text(
                text = "[${selectedTrain.value.first}] ${subwayStart}(${
                    trainInfo.start.format(DateTimeFormatter.ofPattern("hh:mm"))
                }) → ${
                    subwayEnd
                }(${
                    trainInfo.end.format(DateTimeFormatter.ofPattern("hh:mm"))
                })"
            )
            var c = ""
            viewModel.trainSeatList.forEach {
                if (c.isEmpty()) c += it
                else c += ", $it"
            }

            Text(
                text = "${if (selectedTrain.value.second) "특실" else "일반실"} ${viewModel.trainSection}호차 $c"
            )

            val time = LocalDateTime.now().plusMinutes(10)
            Text(
                text = "결제기한: ${time.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh:ss"))}",
                color = Color(0xFFD64400)

            )
            Text(
                text = "10분 이내 미결제시 승차권이 자동으로 취소됩니다.",
                color = Color(0xFFD64400)
            )
        }
    }
}

@Composable
fun TrainTicketCheck.WarnArea() {
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            listOf(
                "승차권을 발권받은 스마트폰에서만 확인할 수 있습니다.",
                "할인 승차권 이용시에는 관련 신분증 또는 증명서를 조시하셔야 합니다."
            ).forEach {
                Text(
                    text = it
                )
            }

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            listOf(
                "본 앱은 열차 예매 연습을 위한 앱입니다.",
            ).forEach {
                Text(
                    text = it
                )
            }
        }


    }

}

@Composable
fun TrainTicketCheck.PayingArea() {
    Row(
        modifier = Modifier
            .height(50.dp)
            .padding(1.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.LightGray),
        ) {
            Text(
                text = "제휴상품",
                color = Color(0xFF000000),
                fontSize = 20.sp
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(Color.LightGray)
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color(0xffcde0ee))
                .weight(1f)
                .setMode(
                    1
                ) {
                  moveNext()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "결제하기",
                color = Color(0xff073b62),
                fontSize = 20.sp
            )

        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrainTicketCheckLayout() {
    PreviewTrainTicketCheck()
}