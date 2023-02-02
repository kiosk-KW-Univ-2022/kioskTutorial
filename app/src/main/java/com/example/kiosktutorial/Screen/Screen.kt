package com.example.kiosktutorial.Screen

sealed class Screen(val route:String){
    object Splash: Screen("splash_screen")
    object Home: Screen("home_screen")
    object KioskTutorialSelection: Screen("kiosk_tutorial_selection_screen")
    object KioskExerciseSelection: Screen("kiosk_exercise_selection_screen")
    object KioskTutorial: Screen("kiosk_tutorial_screen")
    object KioskExercise: Screen("kiosk_exercise_screen")
    object GameHome: Screen("game_home")
    object OfficeHome: Screen("office_home")
    object Bank : Screen("bank")
    object Bank1 : Screen("bank1")
    object Bank1_1 : Screen("bank1_1")
    object Bank2 : Screen("bank2")
    object Bank3 : Screen("bank3")
    object Bank4 : Screen("bank4")
    object Bank5 : Screen("bank5")
    object Bank5_1 : Screen("bank5_1")
    object DoneApp : Screen("doneapp")

}