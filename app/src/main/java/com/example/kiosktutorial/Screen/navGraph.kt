package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay

sealed class BackPress {
    object Idle: BackPress()
    object InitialTouch: BackPress()
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            AnimationSplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            Home(navController)


            var backPressState by remember{ mutableStateOf<BackPress>(BackPress.Idle) }
            var showToast by remember{ mutableStateOf(false)}

            if(showToast){
                Toast.makeText(LocalContext.current, "'뒤로'버튼을 한번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
                showToast= false
            }

            LaunchedEffect(key1 = backPressState){
                if(backPressState == BackPress.InitialTouch){
                    delay(2000)
                    backPressState = BackPress.Idle
                }
            }

            BackHandler(backPressState == BackPress.Idle){
                backPressState =BackPress.InitialTouch
                showToast = true
            }

        }

        composable(route = Screen.KioskTutorialSelection.route){
            KioskSelection(navController = navController, bExercise = false)
        }

        composable(route = Screen.KioskExerciseSelection.route){
            KioskSelection(navController = navController, bExercise = true)
        }

        composable(route = Screen.KioskTutorial.route){
            KioskSelection(navController, false)
        }

        composable(route = Screen.KioskExercise.route){
            KioskSelection(navController, true)
        }
    }
}