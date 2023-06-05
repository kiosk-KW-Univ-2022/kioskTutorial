package com.example.kiosktutorial.Screen.Train

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiosktutorial.R
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TrainSelect.TrainMenu() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff0c3c60))
            .padding(10.dp),

        ) {
        // navigator
        val fontSize = with(LocalDensity.current) { 20.dp.toSp() }
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            val paintD = painterResource(R.drawable.expand_more)
            Icon(
                painter = paintD,
                contentDescription = null,
                modifier = Modifier
                    .setMode(
                        -1,
                        Modifier.scale(0.8f)
                    ) {
                        // only real mode
                        viewModel.navController?.popBackStack()
                    }
                    .rotate(90f),
                tint = Color.White

            )

        }

        // text
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .matchParentSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "열차 조회",
                fontSize = fontSize,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffc6eef5))
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${viewModel.trainSelectData.subwayStart} → ${viewModel.trainSelectData.subwayEnd}",
            fontSize = 20.sp
        )
    }
}

@Composable
fun TrainSelect.TrainDay() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // prev day
        TrainDayChangeButton(text = "이전날")
        // display current day

        Box(
            modifier = Modifier
                .padding(5.dp, 2.dp)
                .setMode(
                    1
                ) {

                }
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            with(viewModel.trainSelectData) {
                Text(
                    text = "${selectDay.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"))} (${selectDay.getDayOfWeekString()})",
                    fontSize = 20.sp
                )

            }
        }

        // next day
        TrainDayChangeButton(text = "다음날")
    }
}

@Composable
fun TrainSelect.TrainDayChangeButton(
    text: String
) {
    val context = LocalContext.current

    OutlinedButton(
        onClick = {
            toast?.cancel()
            toast = Toast.makeText(
                context,
                "${text}의 열차를 검색하는 기능이에요.\n여기에선 제공하지 않아요.",
                Toast.LENGTH_LONG
            )
            toast?.show()
        },
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
        contentPadding = PaddingValues(2.dp, 0.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrainSelect.TrainList() {
    var isExists by remember { mutableStateOf(false) }
    var listState = rememberLazyListState()
    var corutine = rememberCoroutineScope()

    corutine.launch{
        var scrollable = when(getCounter()){
            1-> true
            else-> false
        } or !isTutorial()
        if (scrollable) listState.scroll(scrollPriority = MutatePriority.PreventUserInput) {}
        else listState.scroll(scrollPriority = MutatePriority.PreventUserInput) { awaitCancellation()}
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxHeight()
    )
    {
        item {
            TrainDay()
        }

        stickyHeader {
            TrainDesc()
        }

        itemsIndexed(
            items = trainItemList,
            itemContent = {
                index, item ->
                    TrainSeatItem(item.trainNumber, item.start, item.end)
                isExists = true
                if(item.trainNumber == viewModel.trainSelectData.selectedTrain.value.first){
                    corutine.launch{
                        listState.animateScrollToItem(index)
                    }
                }
            }
        )

        if (!isExists) {
            item {
                Text(
                    text = "오늘 열차는 더이상 운행하지 않습니다.\n다른 날을 이용해 주세요.",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp)
                )
            }
        }

    }
}

fun TrainSelect.funcButtonSelect(train: String, it: Boolean) {
    if (train == viewModel.trainSelectData.selectedTrain.value.first) {
        if (viewModel.trainSelectData.selectedTrain.value.second == it) viewModel.trainSelectData.selectedTrain.value =
            Pair("", false)
        else viewModel.trainSelectData.selectedTrain.value = Pair(train, it)
    } else {
        viewModel.trainSelectData.selectedTrain.value = Pair(train, it)
    }
}

@Composable
fun TrainSelect.TrainSeatItem(
    train: String,
    start: LocalTime,
    end: LocalTime
) {
    var buttonBackground = Pair(Color(0xFFFFFFFF), Color(0xFFFFFFFF))
    val selectedBackground = if (train == viewModel.trainSelectData.selectedTrain.value.first) {

        buttonBackground = when (viewModel.trainSelectData.selectedTrain.value.second) {
            false -> Pair(Color(0xFF89C2FF), Color(0xFFFFFFFF))
            else -> Pair(Color(0xFFFFFFFF), Color(0xFF89C2FF))
        }
        Color(0xFFAEEFFF)
    } else Color(0xFFFFFFF)


    Row(
        modifier = Modifier
            .height(50.dp)
            .background(selectedBackground)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = train,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "${start.format(DateTimeFormatter.ofPattern("HH:mm"))}\n${viewModel.trainSelectData.subwayStart}",
            modifier = Modifier
                .weight(1f), textAlign = TextAlign.Center
        )
        Text(
            text = "${end.format(DateTimeFormatter.ofPattern("HH:mm"))}\n${viewModel.trainSelectData.subwayEnd}",
            modifier = Modifier
                .weight(1f), textAlign = TextAlign.Center
        )




        Box(
            modifier = Modifier
                .padding(5.dp)
                .weight(1f)
                .fillMaxHeight()
                .background(buttonBackground.first)
                .setMode(
                    1,
                    defaultModifier = Modifier

                        .border(2.dp, Color(0xFF195BAD)),
                    overrideModifier = Modifier
                        .border(2.dp, Color.Red)
                ) {
                    funcButtonSelect(train, false)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "30,000원",
                fontSize = 12.sp
            )
        }

        Box(
            modifier = Modifier
                .padding(5.dp)
                .weight(1f)
                .fillMaxHeight()
                .background(buttonBackground.second)
                .setMode(
                    1,
                    defaultModifier = Modifier
                        .border(2.dp, Color(0xFF195BAD)),
                    overrideModifier = Modifier
                        .border(2.dp, Color.Red)
                ) {
                    funcButtonSelect(train, true)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "49,600원",
                fontSize = 12.sp
            )
        }

    }
}

@Composable
fun TrainSelect.TrainDesc() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFB4B4B4))
            .height(25.dp),
    ) {
        val str = listOf("열차", "출발", "도착", "일반식", "특/우등")

        str.forEach {
            Text(
                text = it,
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun TrainSelect.PayingArea() {
    Column(
        modifier = Modifier
            .clickable(false) {}
            .fillMaxWidth(),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF274055))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = if (viewModel.trainSelectData.selectedTrain.value.second) "특실" else "일반실",
                    fontSize = 12.sp,
                    color = Color(0xFF00BCD4),
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                )
                var time: Long = 0
                trainItemList.forEach {
                    if (viewModel.trainSelectData.selectedTrain.value.first == it.trainNumber) {
                        time = Duration.between(it.start, it.end).toMinutes()
                        return@forEach
                    }
                }
                Text(
                    text = "${time / 60}시간 ${time % 60}분",
                    fontSize = 12.sp,
                    color = Color.White

                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)
            ) {
                Text(
                    text = "열차시각",
                    modifier = Modifier
                        .weight(1f),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "운임요금",
                    modifier = Modifier
                        .weight(1f)
                        .drawBehind {
                            val width = size.width
                            val height = size.height
                            drawLine(
                                color = Color.White,
                                start = Offset(0f, 0f),
                                end = Offset(0f, height),
                                strokeWidth = 2f
                            )

                            drawLine(
                                color = Color.White,
                                start = Offset(width, 0f),
                                end = Offset(width, height),
                                strokeWidth = 2f
                            )


                        },
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "좌석선택",
                    modifier = Modifier
                        .weight(1f)
                        .setMode(
                            2,
                            additionalModifier = Modifier
                                .border(2.dp, Color.Red)
                        ) {
                            moveNext()
                        }
                    ,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFADCEDB)),
                contentAlignment = Alignment.Center

            ) {
                Text(
                    text = "예매",
                    fontSize = 26.sp,
                    color = Color(0xFF344672),
                    modifier = Modifier
                        .padding(20.dp)
                )

            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTutorialTrainSelectLayout() {
    PreviewTutorialTrainSelect()
}