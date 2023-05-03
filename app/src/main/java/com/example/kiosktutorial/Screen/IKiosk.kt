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
import kotlin.collections.ArrayList

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
    protected fun IncStep() {
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                ) {
                    design()
                }

                var descBoxModifier: Modifier = GetTutorialDescriptionModifier[GetCounter()] ?: Modifier

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val background = 0xAF000000 or 0x000000

                    Column(
                        modifier = Modifier
                            .background(Color(background)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom

                    ) {
                        Box(
                            modifier = GetTutorialDescriptionDefaultModifier().composed{descBoxModifier},
                            contentAlignment = Alignment.Center
                        ) {
                            if (GetCounter() < GetTutorialDescription.count()) {
                                Text(text = GetTutorialDescription[GetCounter()])
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 40.dp)
                        ) {
                            Button(
                                modifier = Modifier
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
                }
            }
        } else {
            design()
        }
    }

    protected abstract var GetTutorialDescription: ArrayList<String>
    protected abstract var GetTutorialDescriptionModifier: MutableMap<Int, Modifier>

    protected open fun GetTutorialDescriptionDefaultModifier() = Modifier
        .fillMaxWidth()
        .padding(10.dp)


}
