package com.example.kiosktutorial.Screen

import com.example.kiosktutorial.R

data class Kioskicon(
    val name: String,
    val image: Int,
    val route: String
)

object HomeDataProvider {
    val kioskList = listOf(
        Kioskicon("카페 키오스크", R.drawable.coffee, Screen.CafeHome.route),
        Kioskicon("햄버거 키오스크", R.drawable.burger, Screen.HamburgerHome.route),
        Kioskicon("병원 키오스크", R.drawable.hospital, Screen.KioskHospital.route),
        Kioskicon("기차 키오스크", R.drawable.train, Screen.KioskTrain.route),
        Kioskicon("무인발권기 키오스크", R.drawable.selfservice, ""),
        Kioskicon("관공서 키오스크", R.drawable.office, ""),
    )
}