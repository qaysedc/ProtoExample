package com.example.protoexample

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.lifecycle.*
import com.codelab.android.datastore.CounterMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SimpleViewModel(private val repository: SimpleRepository) : ViewModel() {

    val counter = MutableLiveData<Int>()
    init {
        loadData()
    }

    fun loadData(){
        viewModelScope.launch { repository.CounterFlow.collect { counter.value = it} }
    }

    fun updateData(){
        viewModelScope.launch { counter.value?.let { repository.updateCounter(it) } }
    }

    fun onButtonClicked(){
        counter.value = counter.value?.plus(1)
    }

}

class SimpleViewModelFactory(
    private val repository: SimpleRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SimpleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SimpleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}