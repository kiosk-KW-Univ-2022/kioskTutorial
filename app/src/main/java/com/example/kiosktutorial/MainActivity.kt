package com.example.kiosktutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.Screen.SetupNavGraph
import com.example.kiosktutorial.ui.theme.KioskTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            installSplashScreen()
        }
        super.onCreate(savedInstanceState)

        setContent {


            KioskTutorialTheme {
                ActivityFunc()
            }
        }

    }
}

@Composable
fun ActivityFunc(){
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val navController = rememberNavController()
        SetupNavGraph(navController = navController)

    }
}
