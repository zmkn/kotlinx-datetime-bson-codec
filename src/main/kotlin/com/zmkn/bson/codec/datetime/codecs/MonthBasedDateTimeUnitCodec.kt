package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.DateTimeUnit
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class MonthBasedDateTimeUnitCodec : Codec<DateTimeUnit.MonthBased> {
    override fun encode(writer: BsonWriter, value: DateTimeUnit.MonthBased, encoderContext: EncoderContext) {
        writer.writeStartDocument()
        writer.writeInt32("months", value.months)
        writer.writeEndDocument()
    }

    override fun getEncoderClass(): Class<DateTimeUnit.MonthBased> {
        return DateTimeUnit.MonthBased::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): DateTimeUnit.MonthBased {
        reader.readStartDocument()
        val result = DateTimeUnit.MonthBased(reader.readInt32("months"))
        reader.readEndDocument()
        return result
    }
}
