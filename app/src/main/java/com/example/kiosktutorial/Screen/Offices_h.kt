package com.example.kiosktutorial.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kiosktutorial.ui.theme.Typography
import com.example.kiosktutorial.ui.theme.KioskTutorialTheme

@Composable
fun OfficeSelection(navHostController: NavHostController) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        RecyclerViewContent(navHostController)
    }
}


@Composable
fun RecyclerViewContent(navHostController: NavHostController) {
    val offices = remember { DataProvider.officeList }
    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(
            items = offices,
            itemContent = { OfficeListItem(it, navHostController) }
        )
    }
}

@Composable
fun OfficeListItem(office: Office, navHostController: NavHostController) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(0.dp, 12.dp)
            .clickable { if (office.name == "주민등록") navHostController.navigate(Screen.OfficeHome.route) },
        elevation = 4.dp,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = office.name, style = Typography.h6)
                Text(
                    text = office.content,
                    style = Typography.caption,
                    modifier = Modifier.padding(0.dp, 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KioskTutorialTheme {
        var navCtrl = rememberNavController()
        OfficeSelection(navCtrl)
    }
}
