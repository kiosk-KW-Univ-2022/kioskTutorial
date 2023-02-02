package com.example.kiosktutorial.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun KioskSelection(navController: NavHostController, bExercise:Boolean){
    val startPath = if(bExercise) Screen.KioskExercise.route else Screen.KioskTutorial.route

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
    ,   verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ){
                val modeString = if(bExercise) "실전연습" else "따라하기"
                Text(
                    text = "키오스크 $modeString"
                ,   fontSize = 20.sp
                )
                Text(
                    text = "Step1. 진행할 매장을 선택해보세요"
                )
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            onClick = {
                navController.navigate(Screen.KioskTrain.route)
            }
        ){
            Text(
                text = "열차 예매하기"
            )
        }

//        Button(
//            modifier = Modifier
//                .fillMaxWidth()
//                .heightIn(min = 40.dp)
//                .padding(
//                    horizontal = 5.dp
//                )
//        ,   onClick = {
//                // TODO: 실제로 선택한 것을 다시 한번 확인시켜주기
//                navController.navigate(startPath)
//            }
//        ){
//            Text(
//                text = "시작하기"
//            ,   fontSize = 20.sp
//
//            )
//        }
    }

}

@Composable
fun ScreenKioskTutorial(navController:NavHostController) {

}

@Preview(showBackground = true)
@Composable
fun KioskSelectionPreview(){
    val navHostController = rememberNavController()
    KioskSelection(navHostController, false)
}