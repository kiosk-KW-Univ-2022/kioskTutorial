package com.example.kiosktutorial.Screen

import com.example.kiosktutorial.R

data class Kiosk(
    val name: String,
    val image: Int
)
object HomeDataProvider {
    val kioskList = listOf(
        Kiosk("카페 키오스크", R.drawable.coffee),
        Kiosk("햄버거 키오스크", R.drawable.burger),
        Kiosk("은행 키오스크", R.drawable.bank),
        Kiosk("병원 키오스크", R.drawable.hospital),
        Kiosk("기차 키오스크", R.drawable.train),
        Kiosk("무인발권기 키오스크",R.drawable.selfservice ),
        Kiosk("관공서 키오스크", R.drawable.office),
    )
}