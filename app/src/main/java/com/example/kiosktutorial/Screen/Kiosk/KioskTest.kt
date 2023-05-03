package com.example.kiosktutorial.Screen.Kiosk

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kiosktutorial.Screen.IKiosk

class KioskTest(isTutorial: Boolean) : IKiosk(isTutorial) {

    override var GetTutorialDescription = arrayListOf<String>(
        "테스트1 테스트2 테스트1 테스트2 테스트1 테스트2 테스트1 테스트2 테스트1 테스트2 테스트1 테스트2 테스트1 테스트2 "
    )

    override var GetTutorialDescriptionModifier = mutableMapOf<Int, Modifier>(
        1 to Modifier.fillMaxWidth()
    )


    @Composable
    fun MainAct() {
        val context = LocalContext.current
        var toast: Toast? = null
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "${GetCounter()} 번째 / 튜토리얼 모드 ${if(!IsTutorial()) "아님" else ""}"
            )
            Box(
                modifier = Modifier
                    .border(1.dp, Color(0xff000000))
                    .widthIn(20.dp)
                    .heightIn(80.dp)
                    .SetMode(
                        1,
                        defaultModifier = Modifier
                            .background(Color(0xffff00ff)),
                        overrideModifier = Modifier
                            .background(Color(0xff00ff00))
                    ) {
                        toast?.cancel()
                        toast = Toast.makeText(context, "${GetCounter()}번입니다", Toast.LENGTH_SHORT)
                        toast!!.show()
                    }
            ) {
                Text(text ="클릭하면 시작합니다.",
                    modifier = Modifier
                        .SetMode(0){
                            toast?.cancel()
                            toast = Toast.makeText(context, "Hello start with you play $_step", Toast.LENGTH_SHORT)
                            toast!!.show()
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
