package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.DateTimeUnit
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class TimeBasedDateTimeUnitCodec : Codec<DateTimeUnit.TimeBased> {
    override fun encode(writer: BsonWriter, value: DateTimeUnit.TimeBased, encoderContext: EncoderContext) {
        writer.writeStartDocument()
        writer.writeInt64("nanoseconds", value.nanoseconds)
        writer.writeEndDocument()
    }

    override fun getEncoderClass(): Class<DateTimeUnit.TimeBased> {
        return DateTimeUnit.TimeBased::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): DateTimeUnit.TimeBased {
        reader.readStartDocument()
        val result = DateTimeUnit.TimeBased(reader.readInt64("nanoseconds"))
        reader.readEndDocument()
        return result
    }
}
