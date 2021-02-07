package com.example.protoexample

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.codelab.android.datastore.CounterMessage
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object SimpleSerializer : Serializer<CounterMessage> {
    override val defaultValue: CounterMessage = CounterMessage.getDefaultInstance()
    override fun readFrom(input: InputStream): CounterMessage {
        try {
            return CounterMessage.parseFrom(input)
        }catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: CounterMessage, output: OutputStream) = t.writeTo(output)
}