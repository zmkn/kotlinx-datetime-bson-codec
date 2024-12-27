package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.TimeZone
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class TimeZoneCodec : Codec<TimeZone> {
    override fun encode(writer: BsonWriter, value: TimeZone, encoderContext: EncoderContext) {
        writer.writeString(value.id)
    }

    override fun getEncoderClass(): Class<TimeZone> {
        return TimeZone::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): TimeZone {
        return TimeZone.of(reader.readString())
    }
}
