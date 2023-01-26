package com.example.kiosktutorial.Screen

sealed class Screen(val route:String){
    object Splash: Screen("splash_screen")
    object Home: Screen("home_screen")
    object KioskTutorialSelection: Screen("kiosk_tutorial_selection_screen")
    object KioskExerciseSelection: Screen("kiosk_exercise_selection_screen")
    object KioskTutorial: Screen("kiosk_tutorial_screen")
    object KioskExercise: Screen("kiosk_exercise_screen")
    object GameHome: Screen("game_home")
    object CafeHome: Screen("cafe_home")
    object CafeKiosk: Screen("cafe_kiosk")
    object CafeOrder: Screen("cafe_order")
}