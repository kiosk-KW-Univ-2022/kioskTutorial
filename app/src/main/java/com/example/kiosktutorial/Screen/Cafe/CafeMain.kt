package com.example.kiosktutorial.Screen.Cafe

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kiosktutorial.R
import com.example.kiosktutorial.Screen.IKiosk
import com.example.kiosktutorial.Screen.Screen
import com.example.kiosktutorial.Screen.TutorialStepData
import java.time.LocalDate

class CafeMain(isTutorial: Boolean,navHostController: NavHostController? = null) : IKiosk(isTutorial) {

    override var tutorialStepDataList: Map<Int, TutorialStepData> =
        mutableMapOf(
            0 to TutorialStepData(description = "카페 키오스크 튜토리얼을 시작합니다\n시작을 하려면 화면을 눌러주세요",
                stateFunction = {

                }),

        )

    @Composable
    fun MainAct(navHostController: NavHostController? = null) {
        Column(modifier = Modifier .clickable {
            navHostController?.navigate(Screen.CafeKiosk.route)
        })
        {
            StartView();
            Menu()
        }
    }


}

@Preview(showBackground = true)
@Composable
fun PreviewCafeMain() {
    val kioskTest = CafeMain(true,null)
    kioskTest.Layout{
        kioskTest.MainAct()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCafeMainWithReal() {
    val kioskTest = CafeMain(false,null)
    kioskTest.Layout{
        kioskTest.MainAct()
    }
}
