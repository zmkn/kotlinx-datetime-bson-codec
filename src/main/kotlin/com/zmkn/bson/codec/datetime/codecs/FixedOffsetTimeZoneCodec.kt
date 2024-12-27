package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.TimeZone
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class FixedOffsetTimeZoneCodec : Codec<FixedOffsetTimeZone> {
    override fun encode(writer: BsonWriter, value: FixedOffsetTimeZone, encoderContext: EncoderContext) {
        writer.writeString(value.id)
    }

    override fun getEncoderClass(): Class<FixedOffsetTimeZone> {
        return FixedOffsetTimeZone::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): FixedOffsetTimeZone {
        val zone = TimeZone.of(reader.readString())
        return if (zone is FixedOffsetTimeZone) {
            zone
        } else {
            throw IllegalArgumentException("Timezone identifier '$zone' does not correspond to a fixed-offset timezone")
        }
    }
}
