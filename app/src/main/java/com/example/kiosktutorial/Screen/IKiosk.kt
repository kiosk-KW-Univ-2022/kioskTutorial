package com.example.kiosktutorial.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.*

abstract class IKiosk {
    constructor(isTutorial: Boolean) {
        setIsTutorial(isTutorial)
        _step = 0

    }

    protected var _isTutorial: Boolean = false
    protected fun setIsTutorial(value: Boolean) {
        this._isTutorial = value
    }

    protected fun IsTutorial() = _isTutorial


    // this variable will inc/dec when next/prev step in tutorial mode
    private val STEP_MIN = 0
    protected open val STEP_MAX = 300

    protected var _step by mutableStateOf(-1)
    protected fun GetCounter() = _step
    private fun IncStep() {
        if (GetCounter() < STEP_MAX) _step++
    }
    private fun DecStep() {
        if (STEP_MIN < GetCounter()) _step--
    }

    fun Modifier.SetMode(
        step: Int,
        defaultModifier: Modifier = Modifier,
        additionalModifier: Modifier = Modifier,
        overrideModifier: Modifier? = null,
        function: () -> Unit
    ): Modifier {
        // if tutorial mode?
        if (!IsTutorial()) {
            // not
            return this
                .composed { defaultModifier }
                .clickable {
                    function()
                }
        } else {
            // yes
            // is current step are correct sequence?
            if (step == GetCounter()) {
                // yes
                // is modifier override mode?
                if (overrideModifier != null) {
                    //yes
                    return this
                        .composed { overrideModifier }
                        .clickable {
                            function()
                            IncStep()
                        }
                }
                // no
                return this
                    .composed { defaultModifier }
                    .composed { additionalModifier }
                    .clickable {
                        function()
                        IncStep()
                    }
            }
            // no
            return this
                .composed {
                    defaultModifier
                }
        }
    }

    @Deprecated("plz use Modifier.SetMode Instead of this function")
    fun Modifier.SetTutorialMode(
        step: Int,
        mod: Modifier = Modifier,
        func: () -> Unit
    ): Modifier {
        if (IsTutorial() && GetCounter() == step) {
            return this
                .composed { mod }
                .clickable {
                    func()
                    IncStep()
                }
        }
        return this
    }

    @Deprecated("plz use Modifier.SetMode Instead of this function")
    fun Modifier.SetRealMode(mod: Modifier = Modifier, func: () -> Unit): Modifier {
        if (!IsTutorial()) {
            return this
                .composed { mod }
                .clickable {
                    func()
                }
        }
        return this
    }

    @Composable
    open fun Layout(design: @Composable() () -> Unit) {
        if (IsTutorial()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Box(    // content
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                ) {
                    design()
                }

                val tutorialStepData = tutorialStepDataList[GetCounter()]
                var descBoxModifier: Modifier = tutorialStepDataList[GetCounter()]?.GetModifier() ?: Modifier

                Box(    // tutorial description area
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = tutorialStepData?.GetAlignment() ?: defaultTutorialStepData.GetAlignment()!!
                ) {

                    Column(
                        modifier = Modifier
                            .background(Color(tutorialStepData?.GetBackground() ?: defaultTutorialStepData.GetBackground())),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom

                    ) {
                        val align = tutorialStepData?.GetAlignment() ?: defaultTutorialStepData.GetAlignment()
                        if(align == Alignment.TopCenter){
                            TutorialControlButton(tutorialStepData ?: defaultTutorialStepData)
                            TutorialDescription(tutorialStepData ?: defaultTutorialStepData)
                        }else{
                            TutorialDescription(tutorialStepData ?: defaultTutorialStepData)
                            TutorialControlButton(tutorialStepData ?: defaultTutorialStepData)
                        }

                    }
                }
            }
        } else {
            design()
        }
    }

    @Composable
    fun TutorialDescription(tutorialStepData:TutorialStepData){
        Column(
            modifier = tutorialStepData.GetModifier() ?: defaultTutorialStepData.GetModifier()!!,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textComponent = tutorialStepData.GetOverrideTextComponent()
            if(textComponent != null) { // override text Component
                textComponent()
            }else{
                if(GetCounter() in tutorialStepDataList){
                    Text(text = tutorialStepData.GetDescription() ?: "")
                }
            }
        }
    }
    @Composable
    fun TutorialControlButton(tutorialStepData:TutorialStepData){
        Row(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .background(Color(tutorialStepData.GetBackground()))
                .heightIn(max = 40.dp),
            horizontalArrangement = Arrangement.SpaceAround

        ) {
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(),
                enabled = STEP_MIN < GetCounter(),
                onClick = {
                    DecStep()
                }) {
                Text(text = "이전")
            }
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(1f)
                    .fillMaxHeight(),
                enabled = GetCounter() < STEP_MAX,
                onClick = {
                    IncStep()
                }) {
                Text(text = "다음")
            }
        }
    }

    protected abstract var tutorialStepDataList:Map<Int, TutorialStepData>

    protected open val defaultTutorialStepData = TutorialStepData(
        description = null,
        boxModifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        overrideText = null,
        background = 0xA0FFFFFF,
        alignment = Alignment.BottomCenter
    )
}

class TutorialStepData{
    constructor(description:String?, boxModifier:Modifier? = null, overrideText:(@Composable()()->Unit)? = null, background:Long = 0xA0FFFFFF, alignment:Alignment = Alignment.BottomCenter){
        _description = description
        _boxModifier = boxModifier
        _overrideText = overrideText
        _background = background
        _alignment = alignment
    }

    private var _description:String? = null
    private var _boxModifier:Modifier? = null
    private var _overrideText:(@Composable()()->Unit)? = null
    private var _background:Long = 0xA0FFFFFF
    private var _alignment:Alignment = Alignment.BottomCenter
    fun GetDescription() = _description
    fun GetModifier() = _boxModifier
    fun GetOverrideTextComponent() = _overrideText
    fun GetBackground() = _background

    fun GetAlignment() = _alignment
}