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

abstract class IKiosk {
    constructor(isTutorial: Boolean, step:Int = 0) {
        setIsTutorial(isTutorial)
        _step = step
    }

    private var _isTutorial: Boolean = false
    private fun setIsTutorial(value: Boolean) {
        this._isTutorial = value
    }

    protected fun isTutorial() = _isTutorial


    // this variable will inc/dec when next/prev step in tutorial mode
    private val STEP_MIN = 0
    protected open val STEP_MAX = 300

    private var _step by mutableStateOf(-1)
    internal fun getCounter() = _step
    private fun incStep() {
        if (getCounter() < STEP_MAX) _step++
    }
    private fun decStep() {
        if (STEP_MIN < getCounter()) _step--
    }

    fun Modifier.setMode(
        step: Int,
        defaultModifier: Modifier = Modifier,
        additionalModifier: Modifier = Modifier,
        overrideModifier: Modifier? = null,
        function: () -> Unit
    ): Modifier {
        // if tutorial mode?
        if (!isTutorial()) {
            // not
            return this
                .composed { defaultModifier }
                .clickable {
                    function()
                }
        } else {
            // yes
            // is current step are correct sequence?
            if (step == getCounter()) {
                // yes
                // is modifier override mode?
                if (overrideModifier != null) {
                    //yes
                    return this
                        .composed { overrideModifier }
                        .clickable {
                            function()
                            incStep()
                        }
                }
                // no
                return this
                    .composed { defaultModifier }
                    .composed { additionalModifier }
                    .clickable {
                        function()
                        incStep()
                    }
            }
            // no
            return this
                .composed {
                    defaultModifier
                }
        }
    }




    @Composable
    open fun Layout(design: @Composable() () -> Unit) {
        if (isTutorial()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(    // content
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    design()
                }

                val tutorialStepData = tutorialStepDataList[getCounter()]
                var descBoxModifier: Modifier = tutorialStepDataList[getCounter()]?.GetModifier() ?: Modifier

                Box(    // tutorial description area
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = tutorialStepData?.GetAlignment() ?: defaultTutorialStepData.GetAlignment()!!
                ) {

                    Column(
                        modifier = Modifier
                            .clickable(false){}
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
                if(getCounter() in tutorialStepDataList){
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
                enabled = STEP_MIN < getCounter(),
                onClick = {
                    decStep()
                }) {
                Text(text = "이전")
            }
            Button(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(1f)
                    .fillMaxHeight(),
                enabled = getCounter() < STEP_MAX,
                onClick = {
                    incStep()
                }) {
                Text(text = "다음")
            }
        }
    }

    protected open fun init(){
        _step = STEP_MIN

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