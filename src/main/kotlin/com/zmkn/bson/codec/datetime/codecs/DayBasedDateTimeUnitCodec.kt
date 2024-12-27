package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.DateTimeUnit
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class DayBasedDateTimeUnitCodec : Codec<DateTimeUnit.DayBased> {
    override fun encode(writer: BsonWriter, value: DateTimeUnit.DayBased, encoderContext: EncoderContext) {
        writer.writeStartDocument()
        writer.writeInt32("days", value.days)
        writer.writeEndDocument()
    }

    override fun getEncoderClass(): Class<DateTimeUnit.DayBased> {
        return DateTimeUnit.DayBased::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): DateTimeUnit.DayBased {
        reader.readStartDocument()
        val result = DateTimeUnit.DayBased(reader.readInt32("days"))
        reader.readEndDocument()
        return result
    }
}
