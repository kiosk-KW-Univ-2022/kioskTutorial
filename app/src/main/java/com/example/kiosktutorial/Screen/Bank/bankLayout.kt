package com.example.kiosktutorial.Screen.Bank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.Screen
import com.example.kiosktutorial.ui.theme.backGround

//TODO setmode버전으로 바꾸기
@Composable
fun BankMain.SelectMoneyButton(Text:String)
{
    Button(
        shape = RoundedCornerShape(5.dp),
        onClick = { money=Text },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backGround,
        ),
        modifier = Modifier.wrapContentSize()

    ) {
        Text(
            text=Text,
            modifier = Modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BankMain.SelectMoneyButtonyet(Text:String)
{
    Button(
        shape = RoundedCornerShape(5.dp),
        onClick = { money=Text },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.LightGray,
        ),
        modifier = Modifier.wrapContentSize()

    ) {
        Text(
            text=Text,
            modifier = Modifier,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun BankMain.SelectBankwork() {
    Column {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            )
            Column {//위아래 패딩, 옆에 패딩
                SelectMoneyButton("예금출금")
                SelectMoneyButtonyet("입      금")
                SelectMoneyButtonyet("계좌이체")
            }
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            )
            // 오른쪽 버튼
            Column {
                SelectMoneyButtonyet("예금조회")
                SelectMoneyButtonyet("통장정리")
                SelectMoneyButtonyet("신용카드")
            }
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(
                        topStart = CornerSize(25.dp),
                        topEnd = CornerSize(25.dp),
                        bottomEnd = CornerSize(25.dp),
                        bottomStart = CornerSize(25.dp)
                    ), color = backGround
                )
                .height(400.dp)
                .width(380.dp)
                .padding(30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "만원/오만원/수표 출금 가능\n\t\t\t현금/수표 입금가능\n천원권 오처원권 입금불가",
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "\t\t\t\t\t\t\t\t[금융사기 예방 유의사항]\n국세청, 건강보험공단은 현금 입,출금기를 통하여 환급하는 경우는 없습니다.",
                fontSize = 19.sp,
                color = Color.Red
            )
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = "눈이 편한 ATM",
                fontSize = 30.sp
            )
        }
    }
}
@Composable
fun BankMain.SelectOutkind(){//출금종류
    Column {

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            ){
                SelectMoneyButton("통장출금")
            }
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            ){
                SelectMoneyButtonyet("카드출금")
            }
            Box(modifier = Modifier
                .weight(1.2f)
                .fillMaxWidth()
            ) {
                SelectMoneyButtonyet("무통장출금")
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(
                        topStart = CornerSize(25.dp),
                        topEnd = CornerSize(25.dp),
                        bottomEnd = CornerSize(25.dp),
                        bottomStart = CornerSize(25.dp)
                    ), color = backGround
                )
                .height(400.dp)
                .width(380.dp)
                .padding(30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "만원/오만원/수표 출금 가능\n\t\t\t현금/수표 입금가능\n천원권 오처원권 입금불가",
                fontSize = 15.sp
            )
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "\t\t\t\t\t\t\t\t금융사기 예방 유의사항\n국세청, 건강보험공단은 현금 입,출금기를 통하여 환급하는 경우는 없습니다.",
                fontSize = 18.sp,
                color = Color.Red
            )
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = "눈이 편한 ATM",
                fontSize = 30.sp
            )
        }
    }
}
//TODO 확인버튼 색바꾸기
@Composable
fun BankMain.Kardcheck(){
    Column {
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(
                        topStart = CornerSize(25.dp),
                        topEnd = CornerSize(25.dp),
                        bottomEnd = CornerSize(25.dp),
                        bottomStart = CornerSize(25.dp)
                    ), color = Color.LightGray
                )
                .height(130.dp)
                .width(380.dp)
                .padding(30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart), text = "카드를 넣으신 후 확인버튼을 눌러주세요",
                fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
        }
        Box(modifier = Modifier.height(200.dp)) {
            val painterd = painterResource(id = R.drawable.card)
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterd,
                contentDescription = "card",
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .width(90.dp)
            .height(80.dp)
            .shadow(20.dp, shape = RectangleShape, clip = true),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),

            //TODO 온클릭말고 setmode로
            onClick = {  }) {
            Text(
                text = "확인",
                fontSize = 25.sp
            )
        }
    }
}
@Composable
fun BankMain.PrassPassword() {
    var (number, setnumber) = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(//위에 설명구문 박스
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color(0xfffed55f))
        ) {
            Text(
                text = "카드 비밀번호 4자리를 눌러주세요",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Center),
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

        }
        Column(
            modifier = Modifier
                .background(Color.White),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        shape = RoundedCornerShape(
                            topStart = CornerSize(25.dp),
                            topEnd = CornerSize(25.dp),
                            bottomEnd = CornerSize(0),
                            bottomStart = CornerSize(0),
                        ),
                        color = Color.White
                    )
            ) {
                var (text1, Settext1) = remember {
                    mutableStateOf("-")
                }
                var (text2, Settext2) = remember {
                    mutableStateOf("-")
                }
                var (text3, Settext3) = remember {
                    mutableStateOf("-")
                }
                var (text4, Settext4) = remember {
                    mutableStateOf("-")
                }
                if (number >= 1) Settext1("*") else Settext1("-")
                if (number >= 2) Settext2("*") else Settext2("-")
                if (number >= 3) Settext3("*") else Settext3("-")
                if (number >= 4) Settext4("*") else Settext4("-")
                Column(
                    Modifier
                        .padding(top = 10.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = "비밀번호 :  ${text1}  ${text2}  ${text3}  ${text4} ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "   비밀번호는 타인이나 카메라 등에 노출되지 않도록 손이나 책 등으로 가린 후 입력해주세요",
                        fontSize = 16.sp, color = Color.Red
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "1",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "2",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "3",
                        fontSize = 30.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "4",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "5",
                        fontSize = 30.sp
                    )
                }

                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "6",
                        fontSize = 30.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "7",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "8",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "9",
                        fontSize = 30.sp
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(100f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(number - 1) }) {
                    Text(
                        text = "삭제",
                        fontSize = 20.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { if (number < 4) setnumber(number + 1) }) {
                    Text(
                        text = "0",
                        fontSize = 30.sp
                    )
                }
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                    onClick = { setnumber(0) }) {
                    Text(
                        text = "정정",
                        fontSize = 20.sp
                    )
                }
            }
            //TODO setmode로 하기
            if (number == 4) {
                Button(modifier = Modifier
                    .width(90.dp)
                    .height(80.dp)
                    .shadow(20.dp, shape = RectangleShape, clip = true)
                    .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                    onClick = { }) {
                    Text(
                        text = "확인",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
@Composable
fun BankMain.SelectMoney() {
    Column {

        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            )
            Column {//위아래 패딩, 옆에 패딩
                SelectMoneyButton(" 1만원 ")
                SelectMoneyButton(" 3만원 ")
                SelectMoneyButton(" 5만원 ")
                SelectMoneyButton("10만원")
            }
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                    )
            // 오른쪽 버튼
            Column {
                SelectMoneyButton("20만원")
                SelectMoneyButton("25만원")
                SelectMoneyButton("30만원")
                SelectMoneyButton("기    타")
            }
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(
                        topStart = CornerSize(25.dp),
                        topEnd = CornerSize(25.dp),
                        bottomEnd = CornerSize(25.dp),
                        bottomStart = CornerSize(25.dp)
                    ), color = backGround
                )
                .height(300.dp)
                .width(380.dp)
                .padding(30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                text = "찾으실 금액의 버튼을\n하나만 눌러주십시오.",
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier.align(Alignment.Center), text = "ATM 인출한도",
                fontSize = 25.sp, color = Color.Blue
            )
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = "전액 현금: 100만원\n전액 수표:500\n현금+수표:500",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun BankMain.MoneyCheck() {
    Column {
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(3.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = "\t\t명세표를 받으시겠습니까?", fontSize = 25.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(
                        topStart = CornerSize(25.dp),
                        topEnd = CornerSize(25.dp),
                        bottomEnd = CornerSize(25.dp),
                        bottomStart = CornerSize(25.dp)
                    ), color = Color.LightGray
                )
                .height(300.dp)
                .width(380.dp)
                .padding(30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopCenter),
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                text = "찾으시는 금액의 내용입니다", fontSize = 20.sp
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "현금:\t\t\t\t\t 1만원\n수표:\t\t\t\t\t 0만원",
                fontSize = 25.sp
            )
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = "합계금액:\t\t\t\t\t 1만원",
                fontSize = 25.sp
            )


        }
        Spacer(modifier = Modifier.height(20.dp))

        Row() {
            Spacer(modifier = Modifier.width(15.dp))
            Button(modifier = Modifier
                .width(120.dp)
                .height(80.dp)
                .shadow(20.dp, shape = RectangleShape, clip = true),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                onClick = { /*다음페이지로 이동*/ }) {
                Text(
                    text = "아니오",
                    fontSize = 20.sp
                )
            }
            //TODO setmode
            Spacer(modifier = Modifier.width(70.dp))
            Button(modifier = Modifier
                .width(120.dp)
                .height(80.dp)
                .shadow(20.dp, shape = RectangleShape, clip = true),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
                onClick = {  }) {
                Text(
                    text = "예",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun BankMain.KardOut() {
    Column {
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(
                        topStart = CornerSize(25.dp),
                        topEnd = CornerSize(25.dp),
                        bottomEnd = CornerSize(25.dp),
                        bottomStart = CornerSize(25.dp)
                    ), color = Color.LightGray
                )
                .height(130.dp)
                .width(380.dp)
                .padding(30.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center), text = "카드와 명세표를 받으십시오",
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        }
        Box(modifier = Modifier.height(200.dp)) {
            val painterd = painterResource(id = R.drawable.card)
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterd,
                contentDescription = "card",
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .width(90.dp)
            .height(80.dp)
            .shadow(20.dp, shape = RectangleShape, clip = true),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
            //TODO setmode
            onClick = { }) {
            Text(
                text = "확인",
                fontSize = 20.sp
            )
        }
    }
}