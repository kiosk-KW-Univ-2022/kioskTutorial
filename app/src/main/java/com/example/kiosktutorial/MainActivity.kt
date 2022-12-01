package com.example.kiosktutorial

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.Screen.AnimationSplashScreen
import com.example.kiosktutorial.Screen.Home
import com.example.kiosktutorial.Screen.Screen
import com.example.kiosktutorial.Screen.SetupNavGraph
import com.example.kiosktutorial.ui.theme.KioskTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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
