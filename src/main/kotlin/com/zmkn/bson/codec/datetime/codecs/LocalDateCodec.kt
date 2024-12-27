package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.LocalDate
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class LocalDateCodec : Codec<LocalDate> {
    override fun encode(writer: BsonWriter, value: LocalDate, encoderContext: EncoderContext) {
        writer.writeString(value.toString())
    }

    override fun getEncoderClass(): Class<LocalDate> {
        return LocalDate::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): LocalDate {
        return LocalDate.parse(reader.readString())
    }
}
