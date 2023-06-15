package com.example.kiosktutorial.Screen

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.Kiosk.*
import com.example.kiosktutorial.Screen.Train.TrainDataViewModel
import com.example.kiosktutorial.Screen.Train.TrainMain
import com.example.kiosktutorial.Screen.Train.TrainSeat
import com.example.kiosktutorial.Screen.Train.TrainSelect
import kotlinx.coroutines.delay

sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    val startDest: String =
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) Screen.Splash.route
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


            var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
            var showToast by remember { mutableStateOf(false) }

            if (showToast) {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.exitToastMessage),
                    Toast.LENGTH_SHORT
                ).show()
                showToast = false
            }

            LaunchedEffect(key1 = backPressState) {
                if (backPressState == BackPress.InitialTouch) {
                    delay(2000)
                    backPressState = BackPress.Idle
                }
            }

            BackHandler(backPressState == BackPress.Idle) {
                backPressState = BackPress.InitialTouch
                showToast = true
            }

        }

        // TODO: 다른 키오스크의 설명도 나오면 선택하도록 구성
        var trainTutorialViewModel = TrainDataViewModel(0, navController)
        composable(route = Screen.KioskTutorialSelection.route) {
            trainTutorialViewModel.Initialize()
            val trainTutorial = TrainMain(true, trainTutorialViewModel)
            trainTutorial.Layout{
                trainTutorial.MainAct()
            }
            // SecondHome(navController, false)
//        }
//
//        composable(route = "${Screen.KioskTutorialContainer.route}/{route}"){
//            backStackEntry ->
//            val view = KioskTutorialContainer()
//            view.navHostController = navController
//            view.route = backStackEntry.arguments?.getString("route") ?: Screen.CafeHome.route
//            view.View()
        }

        composable(route = "tutorialTrainSelectionAct"){
            val trainSelect = TrainSelect(true, trainTutorialViewModel, trainTutorialViewModel.trainSelectStep)
            trainSelect.Layout{
                trainSelect.MainAct()
            }

        }

        composable(route = "tutorialTrainSeatSelectionAct"){
            val trainSelect = TrainSeat(true,trainTutorialViewModel, 1)
            trainSelect.Layout{
                trainSelect.MainAct()
            }
        }



//        lateinit var test: KioskTest
//        composable(route = "1234test"){
//            test = KioskTest(true)
//            test.Layout{
//                test.MainAct()
//            }
//        }
        setupKioskTest(navController)

        var trainRealViewModel = TrainDataViewModel(0, navController)
        composable(route = Screen.KioskExerciseSelection.route) {
            trainRealViewModel.isInit = true
            SecondHome(navController, true)
        }

        composable(route = Screen.KioskTrain.route) {
//            KioskTrain(navController = navController)
            trainRealViewModel.Initialize()
            val trainTutorial = TrainMain(false, trainRealViewModel)
            trainTutorial.Layout{
                trainTutorial.MainAct()
            }

        }

        composable(route = "realTrainSelectionAct"){
            trainRealViewModel.isInit = false
            val trainSelect = TrainSelect(false, trainRealViewModel, trainRealViewModel.trainSelectStep)
            trainSelect.Layout{
                trainSelect.MainAct()
            }

        }
        composable(route = "realTrainSeatSelectionAct"){
            val trainSelect = TrainSeat(false,trainRealViewModel, 1)
            trainSelect.Layout{
                trainSelect.MainAct()
            }
        }
        composable(route = Screen.KioskTutorial.route) {
            trainTutorialViewModel.isInit = true
            Secondhome2(navController)
        }

        composable(route = Screen.GameHome.route) {
            GameHomeScreen(navController)
        }
        composable(route = Screen.TextGame.route) {
            GamePlayScreen(navController, "글자색 맞추기")
        }
        composable(route = Screen.NumberGame.route) {
            GamePlayScreen(navController, "숫자 순서 맞추기")
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
        composable(route = Screen.HamburgerHome.route)
        {
            HamburgerMainScreen(navController)
        }
        composable(route = Screen.HamburgerKiosk.route)
        {
            HambergurKiosk(navController)
        }
        composable("${Screen.PayWindow.route}/{move}")
        { backStackEntry ->
            Paywindow(
                navHostController = navController,
                route = backStackEntry.arguments?.getString("move") ?: "${Screen.CafeOrder.route}"
            )
        }
        composable(Screen.Bank.route)
        {
            Secondhome2(navController)
        }
        composable(Screen.Bank1.route)
        {
            Secondhome1(navController)
        }

        composable(Screen.Bank1_1.route)
        {
            Secondhome1_1(navController)
        }

        composable(Screen.Bank2.route)
        {
            InputNumber(navController)
        }
        composable(Screen.Bank3.route)
        {
            Secondhome3(navController)
        }
        composable(Screen.Bank4.route)
        {
            Secondhome4(navController)
        }
        composable(Screen.Bank5.route)
        {
            Secondhome5(navController)
        }
        composable(Screen.Bank5_1.route)
        {
            Secondhome5_1(navController)
        }
        composable("${Screen.DoneApp.route}/{move}")
        { backStackEntry ->
            Paywindow2(
                navHostController = navController,
                route = backStackEntry.arguments?.getString("move") ?: "${Screen.Home.route}"
            )
        }
        composable(Screen.Offices_h.route){
            officehome(navController)
        }
        composable(Screen.Offices_resident.route){
            officehome2(navController)
        }
        composable(Screen.Offices_resident_1.route){
            InputNumber2(navController)
        }
        composable(Screen.Offices_resident_2.route){
            fingerpoint(navController)
        }
        composable(Screen.Offices_resident_4.route){
            officehome4(navController)
        }
        composable(Screen.Offices_resident_5.route){
            InputNumber3(navController)
        }

        val hvModel = HospitalVModel()
        composable(route = Screen.KioskHospital.route){
            hvModel.initData()
            HospitalMain(navController, hvModel)

            BackHandler(true){
                hvModel.bInit = true
                navController.popBackStack()
            }
        }

        composable(route = Screen.KioskHospitalCheck.route){
            hvModel.bInit = true
            HospitalCheck(navController, hvModel)
        }

    }


}

fun NavGraphBuilder.setupKioskTest(navController: NavController){
    navigation(startDestination = "kioskTestTutorial", route = "kioskTest"){
        val tutorial = KioskTest(true)
        val real = KioskTest(false)
        composable(route = "kioskTestTutorial"){
            tutorial.Layout{
                tutorial.MainAct()
            }
        }

        composable(route = "kioskTestReal"){
            real.Layout{
                real.MainAct()
            }
        }
    }
}