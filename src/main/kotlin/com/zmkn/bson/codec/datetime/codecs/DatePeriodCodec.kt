package com.zmkn.bson.codec.datetime.codecs

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimePeriod
import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class DatePeriodCodec : Codec<DatePeriod> {
    override fun encode(writer: BsonWriter, value: DatePeriod, encoderContext: EncoderContext) {
        writer.writeString(value.toString())
    }

    override fun getEncoderClass(): Class<DatePeriod> {
        return DatePeriod::class.java
    }

    override fun decode(reader: BsonReader, decoderContext: DecoderContext): DatePeriod {
        return when (val period = DateTimePeriod.parse(reader.readString())) {
            is DatePeriod -> period
            else -> throw IllegalArgumentException("$period is not a date-based period")
        }
    }
}
