package com.example.kiosktutorial.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

abstract class IKiosk {
    constructor(isTutorial:Boolean){
        setIsTutorial(isTutorial)
        _step = 0

    }

    protected var _isTutorial:Boolean = false
    protected fun setIsTutorial(value:Boolean){
        this._isTutorial = value
    }
    protected fun IsTutorial() = _isTutorial


    // this variable will inc/dec when next/prev step in tutorial mode
    protected var _step by mutableStateOf(-1)
    protected fun GetCounter() = _step
    protected fun IncStep(){
        _step = _step + 1
    }

    fun Modifier.SetMode(step:Int,
                         defaultModifier:Modifier = Modifier,
                         tutorialModifier:Modifier = Modifier,
                         additionalModifier:Modifier? = null,
                         function:()->Unit
    ):Modifier{
        if(!IsTutorial()){
            return this
                .composed{defaultModifier}
                .clickable{
                    function()
                }
        }
        else{
            if(step == GetCounter()){
                if(additionalModifier != null){
                    return this
                        .composed{defaultModifier}
                        .composed{additionalModifier}
                        .clickable{
                            function()
                            IncStep()
                        }
                }
                return this
                    .composed{tutorialModifier}
                    .clickable{
                        function()
                        IncStep()
                    }
            }
            return this
                .composed{
                    defaultModifier
                }
        }
    }

    @Deprecated("plz use Modifier.SetMode Instead of this function")
    fun Modifier.SetTutorialMode(step:Int,
                                 mod:Modifier = Modifier,
                                 func:()->Unit):Modifier{
        if(IsTutorial() && GetCounter() == step){
            return this.composed{mod}
                .clickable{
                func()
                IncStep()
            }
        }
        return this
    }

    @Deprecated("plz use Modifier.SetMode Instead of this function")
    fun Modifier.SetRealMode(mod:Modifier = Modifier, func:()->Unit):Modifier{
        if(!IsTutorial()){
            return this.composed{mod}
                .clickable{
                func()
            }
        }
        return this
    }

    @Composable
    open fun Layout(design:@Composable()()->Unit){
        if(IsTutorial()){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                ){
                    design()
                }
                Box(
                    modifier = Modifier
                        .background(Color(0xFF888888))
                        .fillMaxWidth()
                        .fillMaxHeight(1f)
                ){

                }
            }
        }else{
            design()
        }
    }

    protected abstract fun GetTutorialDescrption():ArrayList<String>

}
