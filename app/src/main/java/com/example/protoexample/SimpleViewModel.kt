package com.example.protoexample

import androidx.datastore.core.DataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codelab.android.datastore.CounterMessage

class SimpleViewModel : ViewModel() {
    val counter = MutableLiveData<Int>()
    init {
        counter.value = 0
    }

    fun onButtonClicked(){
        counter.value = counter.value?.plus(1)
    }

}