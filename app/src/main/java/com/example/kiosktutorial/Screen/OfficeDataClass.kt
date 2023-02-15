package com.example.kiosktutorial.Screen

import com.example.kiosktutorial.R

data class Office(
    val name: String,
    val content: String,
    val image: Int
)

object DataProvider {
    val officeList = listOf(
        Office("주민등록", "200원",R.drawable.document),
        Office("가족관계등록부","500원",R.drawable.document),
        Office("건강보험(국민연금)", "무료",R.drawable.document)
    )

    val residnetList = listOf(
        Office("주민등록표(초본)", "200원",R.drawable.document),
        Office("주민등록표(등본)","200원",R.drawable.document)
    )
}