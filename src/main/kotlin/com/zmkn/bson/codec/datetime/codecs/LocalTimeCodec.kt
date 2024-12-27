package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.LocalTime
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class LocalTimeCodec : Codec<LocalTime> {
    override fun encode(writer: BsonWriter, value: LocalTime, encoderContext: EncoderContext) {
        writer.writeString(value.toString())
    }

    override fun getEncoderClass(): Class<LocalTime> {
        return LocalTime::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): LocalTime {
        return LocalTime.parse(reader.readString())
    }
}
