package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.LocalDateTime
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class LocalDateTimeCodec : Codec<LocalDateTime> {
    override fun encode(writer: BsonWriter, value: LocalDateTime, encoderContext: EncoderContext) {
        writer.writeString(value.toString())
    }

    override fun getEncoderClass(): Class<LocalDateTime> {
        return LocalDateTime::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): LocalDateTime {
        return LocalDateTime.parse(reader.readString())
    }
}
