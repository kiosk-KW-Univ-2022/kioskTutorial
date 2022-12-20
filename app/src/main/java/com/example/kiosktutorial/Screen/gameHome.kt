package com.example.kiosktutorial.Screen

import android.content.res.Configuration.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun GameHomeScreen(navHostController: NavHostController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ){

    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun PreviewGameHome(){
    val navCtrl = rememberNavController()
    GameHomeScreen(navCtrl)
}
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewDarkGameHome(){
    val navCtrl = rememberNavController()
    GameHomeScreen(navCtrl)
}