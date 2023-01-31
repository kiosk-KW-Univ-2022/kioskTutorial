package com.example.kiosktutorial.Screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.Kiosk.KioskTrain
import kotlinx.coroutines.delay

sealed class BackPress {
    object Idle: BackPress()
    object InitialTouch: BackPress()
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val startDest:String =
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) Screen.Splash.route
        else Screen.Home.route

    NavHost(
        navController = navController,
        startDestination = startDest
    ) {
        composable(route = Screen.Splash.route) {
            AnimationSplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            Home(navController)


            var backPressState by remember{ mutableStateOf<BackPress>(BackPress.Idle) }
            var showToast by remember{ mutableStateOf(false)}

            if(showToast){
                Toast.makeText(LocalContext.current, stringResource(R.string.exitToastMessage), Toast.LENGTH_SHORT).show()
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
            CafeMainScreen(navController)
        }

        composable(route = Screen.KioskTrain.route){
            KioskTrain(navController = navController)
        }

        composable(route = Screen.KioskTutorial.route){
            KioskSelection(navController, false)
        }

        composable(route = Screen.KioskExercise.route){
            KioskSelection(navController, true)
        }
        composable(route = Screen.GameHome.route){
            GameHomeScreen(navController)
        }
        composable(route = Screen.TextGame.route){
            GamePlayScreen(navController, "글자색 맞추기")
        }
        composable(route = Screen.NumberGame.route){
            GamePlayScreen(navController, "숫자 순서 맞추기" )
        }
        composable(route = Screen.CafeKiosk.route)
        {
            CafeKiosk(navController)
        }
        composable(route = Screen.CafeHome.route)
        {
            CafeMainScreen(navController)
        }
        composable(route = Screen.CafeOrder.route)
        {
            CafeOrder(navController)
        }
        composable("${Screen.PayWindow.route}/{move}")
        {backStackEntry ->
            paywindow(navHostController = navController,route = backStackEntry.arguments?.getString("move") ?:"${Screen.CafeOrder.route}")
        }
    }
}