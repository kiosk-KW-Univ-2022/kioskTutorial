package com.example.kiosktutorial.Screen.Kiosk

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun KioskTrain(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6E95DB))
                .padding(10.dp),
            contentAlignment = Alignment.Center

        ){
            Text(text = "승차권 예매", fontSize = 18.sp)
        }

        val pagerState = rememberPagerState(0)


        Tabs(pagerState = pagerState)
        TabContent(pagerState = pagerState)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState){
    val pages = listOf("편도", "왕복")

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.background,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier
                    .pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = Color(0xff6e95db)
            )
        }

    ) {
        pages.forEachIndexed{
            index, _ ->
            Tab(selected = pagerState.currentPage == index,
                text = {
                    Text(
                        text = pages[index],
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                },
                onClick = {
                scope.launch{
                    pagerState.animateScrollToPage(index)
                }
            })
        }
    }

}

@ExperimentalPagerApi
@Composable
fun TabContent(pagerState: PagerState){
    HorizontalPager(state = pagerState, count = 2) {
        page ->
        when(page){
            0 -> DirectionalTicket()
            1 -> BiDirectionalTicket()
        }
    }
}

@Composable
fun DirectionalTicket(){
    Text(text="1 page")
}

@Composable
fun BiDirectionalTicket(){
    Text(text="2 page")

}

// light mode
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewKioskTrain(){
    KioskTrain(rememberNavController())
}