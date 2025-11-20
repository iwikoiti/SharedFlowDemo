package com.example.sharedflowdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class DemoViewModel: ViewModel() {
    //Изменяемый поток
    private val _sharedFlow = MutableSharedFlow<Int>()
    //Только для чтения, версия потока
    val sharedFlow = _sharedFlow.asSharedFlow()

    //Когда создается ViewModel, сразу вызывается
    init {
        sharedFlowInit()
    }

    //Функция запуска корутины (корутина отменяется, когда VM уничтожается)
    private fun sharedFlowInit() {
        viewModelScope.launch {
            for (i in 1..1000) {
                delay(2000)
                println("Emmiting $i")
                _sharedFlow.emit(i)
            }
        }
    }
}