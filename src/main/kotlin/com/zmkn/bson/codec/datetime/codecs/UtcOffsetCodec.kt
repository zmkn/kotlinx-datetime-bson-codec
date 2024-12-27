package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.UtcOffset
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class UtcOffsetCodec : Codec<UtcOffset> {
    override fun encode(writer: BsonWriter, value: UtcOffset, encoderContext: EncoderContext) {
        writer.writeString(value.toString())
    }

    override fun getEncoderClass(): Class<UtcOffset> {
        return UtcOffset::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): UtcOffset {
        return UtcOffset.parse(reader.readString())
    }
}
