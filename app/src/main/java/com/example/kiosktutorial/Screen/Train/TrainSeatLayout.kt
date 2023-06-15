package com.example.kiosktutorial.Screen.Train

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiosktutorial.R

@Composable
fun TrainSeat.Title() {
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
                text = "${selectedTrainSection+1}호차 좌석조회",
                fontSize = fontSize,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}



@Composable
fun TrainSeat.TrainSectionSelector() {

    val tutorialCorrection = isTutorial() && getCounter() == 2

    fun innerText(section: Int, rest: Int) = "${section}호차 (${rest}석)"

    Box(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Text(
            text = innerText(selectedTrainSection+1, getRestSeat(selectedTrainSection)),
            modifier = Modifier
                .fillMaxWidth()
                .setMode(
                    1,
                    defaultModifier = Modifier
                        .border(1.dp, Color.Gray),
                    overrideModifier = Modifier
                        .border(1.dp, Color.Red)
                ) {
                    comboboxExpanded = !comboboxExpanded
                }
                .padding(10.dp)
        )

        DropdownMenu(
            expanded = comboboxExpanded,
            onDismissRequest = { if(getCounter() != 2 && !isTutorial()) comboboxExpanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            for (i in 0 until sectionCount)
                DropdownMenuItem(
                    onClick =
                    {
                        selectedTrainSection = i
                        comboboxExpanded = false
                        forceModifyingStep(3)
                    }
                ) {
                    Text(
                        text = innerText(i, getRestSeat(i))
                    )
                }
        }

    }

}

@Composable
fun TrainSeat.SeatSelectArea(){
    val vScroll = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxHeight()
            .verticalScroll(vScroll)
    ){

        for(i in 0 until 10){
            SeatLine(i, i < 5)
        }
        Box(
            modifier = Modifier
                .height(100.dp)
        ){

        }
    }
}

@Composable
fun TrainSeat.SeatLine(
    code:Int,
    isReverseDirection:Boolean
){
    @Composable
    fun RowScope.SeatElement(
        code:String,
        seatColor:Color
    ){
        val exists = this@SeatLine.selectedSeat.contains(code)
        val col =
            if(exists) Color(0xFF00377A)
            else seatColor

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(3.dp)
                .background(col)
                .weight(1f)
                .setMode(
                    3
                ) {
                    this@SeatLine.SelectSeatXor(code)
                },
            contentAlignment = Alignment.Center
        ){
            Text(
                color = if(exists) Color.White else Color.Black,
                text = code
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .height(60.dp),

    ){
        val sc = when(isReverseDirection){
            true -> seatColor[0]
            else -> seatColor[1]
        }
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(3.dp, 0.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.Start
        ){

            SeatElement("${code+1}A", sc)
            SeatElement("${code+1}B", sc)

        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(3.dp, 0.dp)
                .weight(1f),
            contentAlignment = Alignment.Center
        ){
            Text(
                text="▲",
                fontSize = 30.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(3.dp, 0.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.End
        ){
            SeatElement(code = "${code+1}C", seatColor = sc)
            SeatElement(code = "${code+1}D", seatColor = sc)
        }

    }
}

@Composable
fun TrainSeat.SelectViewArea(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text ="선택 좌석",
            color = Color.White
        )
        var totalCount = 0;
        viewModel.trainSelectData.personTicket.forEach{
            totalCount += it.value.value;
        }

        Text(
            text = "${selectedSeat.count()}명의 좌석 선택 / 총 ${totalCount}명",
            color = Color.White
        )

        var c = "";
        if(selectedSeat.isNotEmpty()){
            c = "${selectedTrainSection}호차 "
            c += selectedSeat[0]

            for(i in 1 until selectedSeat.count()){
                c += ", ${selectedSeat[i]}"
            }
        }
        Text(
            text = "$c",
            color = Color.White
        )

        if(selectedSeat.isNotEmpty()){
            Text(
                color = if(totalCount == selectedSeat.count()) Color(0xFF1C2644) else Color(0xA01C2644),
                text = "선택 완료",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFB7CCFF))
                    .padding(10.dp)
                    .setMode(4){
                        moveNext()
                    }
                ,
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrainSeatLayout(){
    PreviewTutorialTrainSeat()
}
