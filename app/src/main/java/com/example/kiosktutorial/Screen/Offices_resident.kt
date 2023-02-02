package com.example.kiosktutorial.Screen

import androidx.compose.foundation.BorderStroke
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
import com.example.kiosktutorial.ui.theme.Typography
import com.example.kiosktutorial.ui.theme.KioskTutorialTheme

@Composable
fun ResidentSelection(){
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        RecyclerViewContent2()
    }
}


@Composable
fun RecyclerViewContent2() {
    val offices = remember { DataProvider.residnetList }
    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(
            items = offices,
            itemContent = { ResidentListItem(it) }
        )
    }
}

@Composable
fun ResidentListItem(office: Office) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 12.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = office.name, style = Typography.h6)
                Text(text = office.content, style = Typography.caption, modifier = Modifier.padding(0.dp,2.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    KioskTutorialTheme {
        RecyclerViewContent2()
    }
}
