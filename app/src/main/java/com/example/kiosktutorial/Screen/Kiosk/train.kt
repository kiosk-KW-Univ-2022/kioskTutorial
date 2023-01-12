package com.example.kiosktutorial.Screen.Kiosk

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun KioskTrain(navController: NavHostController){

}

// light mode
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewKioskTrain(){
    KioskTrain(rememberNavController())
}