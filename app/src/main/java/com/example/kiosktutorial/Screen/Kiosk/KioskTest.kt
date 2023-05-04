package com.example.kiosktutorial.Screen.Kiosk

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.TutorialStepData

class KioskTest(isTutorial: Boolean) : IKiosk(isTutorial) {

    override var tutorialStepDataList: Map<Int, TutorialStepData> =
        mutableMapOf(
            1 to TutorialStepData("hello world"),
            2 to TutorialStepData("this is second step"),
            3 to TutorialStepData(
                "third step is applied custom modifier that change padding and make border",
                boxModifier = Modifier
                    .padding(15.dp)
                    .border(width = 2.dp, color = Color(0xFFFFFFFF))
                    .padding(10.dp, 5.dp),
                background = 0xFF000000 or 0x045526
            ),
            4 to TutorialStepData(
                description = "this text will be ignored",
                background = 0xFF000000 or 0xffe65a,
                overrideText = {
                    Text(
                        text = "4th step is introduce override inner Text component. if this parameter is not null, description string will be ignored."
                    )
                }),
            5 to TutorialStepData(
                description = "description area can setting top of screen",
                alignment = Alignment.TopCenter
            )
        )

    @Composable
    fun MainAct() {
        val context = LocalContext.current
        var toast: Toast? = null
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "${getCounter()} 번째 / 튜토리얼 모드 ${if(!isTutorial()) "아님" else ""}"
            )
            Box(
                modifier = Modifier
                    .border(1.dp, Color(0xff000000))
                    .widthIn(20.dp)
                    .heightIn(80.dp)
                    .setMode(
                        1,
                        defaultModifier = Modifier
                            .background(Color(0xffff00ff)),
                        overrideModifier = Modifier
                            .background(Color(0xff00ff00))
                    ) {
                    }
            ) {
                Text(text ="클릭하면 시작합니다.",
                    modifier = Modifier
                        .setMode(0){
                        }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewKioskTestWithTutorial() {
    val kioskTest = KioskTest(true)
    kioskTest.Layout{
        kioskTest.MainAct()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewKioskTestWithReal() {
    val kioskTest = KioskTest(false)
    kioskTest.Layout{
        kioskTest.MainAct()
    }
}
