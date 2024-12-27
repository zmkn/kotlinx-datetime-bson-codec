package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.DateTimeUnit
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class DateTimeUnitCodec : Codec<DateTimeUnit> {
    override fun encode(writer: BsonWriter, value: DateTimeUnit, encoderContext: EncoderContext) {
        writer.writeStartDocument()
        writer.writeString("type", value::class.simpleName ?: "Unknown")
        when (value) {
            is DateTimeUnit.DayBased -> writer.writeInt32("days", value.days)
            is DateTimeUnit.MonthBased -> writer.writeInt32("months", value.months)
            is DateTimeUnit.TimeBased -> writer.writeInt64("nanoseconds", value.nanoseconds)
        }
        writer.writeEndDocument()
    }

    override fun getEncoderClass(): Class<DateTimeUnit> {
        return DateTimeUnit::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): DateTimeUnit {
        reader.readStartDocument()
        val result = when (val type = reader.readString("type")) {
            DateTimeUnit.DayBased::class.simpleName -> {
                reader.readInt32("days").let { DateTimeUnit.DayBased(it) }
            }

            DateTimeUnit.MonthBased::class.simpleName -> {
                reader.readInt32("months").let { DateTimeUnit.MonthBased(it) }
            }

            DateTimeUnit.TimeBased::class.simpleName -> {
                reader.readInt64("nanoseconds").let { DateTimeUnit.TimeBased(it) }
            }

            else -> throw IllegalArgumentException("Unknown DateTimeUnit type: $type")
        }
        reader.readEndDocument()
        return result
    }
}
