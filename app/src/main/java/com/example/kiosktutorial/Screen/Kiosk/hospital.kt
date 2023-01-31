package com.example.kiosktutorial.Screen.Kiosk

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
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

class HospitalVModel : ViewModel() {
    var id by mutableStateOf("")

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
        id = ""
    }
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
        Text(
            text = "수납/처방전 발행",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xfffbe872))
                .padding(10.dp),
            textAlign = TextAlign.Center,
            fontSize = 28.sp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp)
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
                                    text = "$code"
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
                        Text("정정")
                    }
                    Button(
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f),
                        onClick = {
                            viewModel.addId("0")
                        }
                    ){
                        Text("0")
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

                            toast?.cancel()
                            toast = Toast.makeText(context, "수납 금액/처방전을 확인하세요", Toast.LENGTH_SHORT)
                            toast!!.show()

                        }
                    ){
                        Text("확인")
                    }

                }

            }


        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHospitalMain() {
    HospitalMain(rememberNavController())
}