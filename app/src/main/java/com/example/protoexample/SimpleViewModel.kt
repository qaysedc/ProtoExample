package com.example.protoexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SimpleViewModel : ViewModel() {
    val counter = MutableLiveData<Int>()
    init {
        counter.value = 0
    }

    fun onButtonClicked(){
        counter.value = counter.value?.plus(1)
    }

}