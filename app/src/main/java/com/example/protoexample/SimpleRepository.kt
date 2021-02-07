package com.example.protoexample

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import com.codelab.android.datastore.CounterMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SimpleRepository(context: Context) {
    private val dataStore: DataStore<CounterMessage> = context.createDataStore(
        fileName = "counterExample.pb",
        serializer = SimpleSerializer
    )

    val messageFlow: Flow<CounterMessage> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("SimpleRepository", "Error reading counter example", exception)
                emit(CounterMessage.getDefaultInstance())
            }else{
                throw exception
            }
        }

    val CounterFlow: Flow<Int> = messageFlow
        .map { value:CounterMessage -> value.counter }


    suspend fun updateCounter(counter: Int){
        dataStore.updateData { data -> data.toBuilder().setCounter(counter).build() }
    }

    companion object {
        @Volatile
        private var INSTANCE: SimpleRepository? = null

        fun getInstance(context: Context): SimpleRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }

                val instance = SimpleRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }

}