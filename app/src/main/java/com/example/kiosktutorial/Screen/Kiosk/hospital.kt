package com.example.kiosktutorial.Screen.Kiosk

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.Screen

class HospitalVModel : ViewModel() {
    var id by mutableStateOf("")
    var bInit = false
    var payingCheck by mutableStateOf("")

    fun addId(value: String) {
        if (id.length < 6 && value.isNotEmpty())
            id = "$id${value.substring(0, 1)}"
    }

    fun clearId(){
        id = ""
    }

    init {
        initData()
    }

    fun initData() {
        if(!bInit) return

        id = ""
    }
}

@Composable
fun Title(){
    Text(
        text = "수납/처방전 발행",
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xfffbe872))
            .padding(10.dp),
        textAlign = TextAlign.Center,
        fontSize = 28.sp
    )
}

@Composable
fun HospitalMain(
    navHostController: NavHostController,
    viewModel: HospitalVModel = HospitalVModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Title()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "영수증 바코드를 아래 리더기에 대주세요.",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .weight(1f)
                )
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "바코드 예시",
                        fontSize = 24.sp
                    )
                    val paintD = painterResource(id = R.drawable.kiosk_qr_example)
                    Image(
                        painter = paintD,
                        contentDescription = null,
                    )

                }
            }

            Spacer(
                modifier = Modifier
                    .padding(0.dp, 5.dp)
                    .fillMaxWidth(0.98f)
                    .height(1.dp)
                    .background(Color(0xff555555))
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "영수증이 없는 경우\n주민등록번호를 입력해주세요",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                var code = ""
                for(i:Int in viewModel.id.length..5) code = "$code _"
                Text(
                    text = "${viewModel.id}$code - XXXXXXX",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 26.sp
                )
            }

            // key-pad
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp)
            ) {
                for (i: Int in 0..2) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        for (k: Int in 1..3) {
                            val code = i * 3 + k
                            Button(
                                modifier = Modifier
                                    .padding(5.dp)
                                    .weight(1f),
                                onClick = {
                                    viewModel.addId("$code")
                                }
                            ){
                                Text(
                                    text = "$code",
                                    fontSize = 30.sp
                                )
                            }
                        }
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()){
                    Button(
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f),
                        onClick = {
                            viewModel.clearId()
                        }
                    ){
                        Text("정정", fontSize = 30.sp)
                    }
                    Button(
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f),
                        onClick = {
                            viewModel.addId("0")
                        }
                    ){
                        Text("0", fontSize = 30.sp)
                    }
                    val context = LocalContext.current
                    var toast: Toast? = null
                    Button(
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f),
                        onClick = {
                            if(viewModel.id.length != 6){
                                toast?.cancel()
                                toast = Toast.makeText(context, "주민등록번호를 모두 입력해주세요.", Toast.LENGTH_SHORT)
                                toast!!.show()
                                return@Button
                            }
                            navHostController.popBackStack()
                            navHostController.navigate(Screen.KioskHospitalCheck.route)
                        }
                    ){
                        Text("확인", fontSize = 30.sp)
                    }

                }

            }


        }


    }
}

@Composable
fun HospitalCheck(
    navHostController:NavHostController,
    viewModel:HospitalVModel
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Title()

            Text(
                text = "수납 금액을 확인하시고 결제를 진행해주세요",
                modifier= Modifier
                    .padding(5.dp, 10.dp, 5.dp, 0.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Row(
                modifier  = Modifier
                    .padding(20.dp)
                    .border(2.dp, Color(0xff888888))
                    .padding(20.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically

            ){
                val size = 25.sp

                Text(
                    text = "총 수납액: ",
                    fontSize = size
                )

                Text(
                    text ="56,500",
                    color = Color(0xFFFF5151),
                    fontSize = size
                )
                Text(
                    text = "원",
                    fontSize = size
                )
            }

            val list =arrayOf(
                arrayOf("진료과", "수납액", "할부"),
                arrayOf("XXXX과", "56,500원", "가능"),
                arrayOf("","","")
            )

            for(i:Int in 0..2){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                ){
                    val colour =
                        if(i == 0) 0x00
                        else 0xFF888888

                    for(k:Int in 0..2){
                        Text(
                            text =list[i][k],
                            modifier = Modifier
                                .weight(if (k == 2) 2f else 4f)
                                .padding(3.dp)
                                .border(2.dp, Color(colour))
                                .padding(5.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 26.sp

                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(0.98f)
                    .height(1.dp)
                    .background(Color(0xff888888))
            )

            CardPayingSelectionButton(viewModel)

        }

        if(viewModel.payingCheck.isNotEmpty()){
            ReCheck(navHostController,viewModel)
        }
    }

}

@Composable
fun CardPayingSelectionButton(viewModel: HospitalVModel){
    val list = arrayOf(
        "일시불", "2개월", "3개월", "4개월", "5개월", "6개월", "9개월", "12개월"
    )

    Text(
        text = "일시불부터 최대 12개월까지\n분할 납부가 가능합니다",
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()

    )

    for(i:Int in 0..3){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp, 0.dp)

        ){
            for(k:Int in 0..1){
                Button(
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f)
                        //.background(Color(0xfffbe872))
                        .padding(5.dp),
                    onClick = {
                        viewModel.payingCheck = list[i*2+k]
                    }
                ){
                    Text(
                        text = list[i*2+k],
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun ReCheck(navHostController: NavHostController, viewModel:HospitalVModel){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xA0000000))
                .clickable(false){
                    viewModel.payingCheck = ""
                },
            contentAlignment = Alignment.Center
        ){

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color(0xffffffff))
                    .padding(10.dp, 20.dp)
                    .clickable(false){},
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                Text(
                    text = "${viewModel.payingCheck}로 결제를 진행합니다.",
                    fontSize = 26.sp,
                    modifier = Modifier
                        .padding(10.dp)
                )

                Row(

                ){

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        onClick = {
                            // TODO: going to payment page
                        }
                    ){
                        Text(
                            text = "결제 진행",
                            fontSize = 24.sp
                        )
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        onClick = {
                            viewModel.payingCheck = ""
                        }
                    ){
                        Text(
                            text = "다시 선택",
                            fontSize = 24.sp
                        )
                    }
                }


            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHospitalCheck(){
    val td = HospitalVModel()
    for(i:Int in 1..5){
        td.addId("$i")
    }
    
    td.payingCheck = "일시불"
    
    HospitalCheck(rememberNavController(), td)
}

@Preview(showBackground = true)
@Composable
fun PreviewHospitalMain() {
    HospitalMain(rememberNavController())
}
