package com.zmkn.bson.codec.datetime

import com.zmkn.bson.codec.datetime.codecs.*
import org.bson.codecs.Codec

object DatetimeBsonCodec {
    val datePeriodCodec = {
        DatePeriodCodec()
    }

    val dateTimePeriodCodec = {
        DateTimePeriodCodec()
    }

    val dateTimeUnitCodec = {
        DateTimeUnitCodec()
    }

    val dayBasedDateTimeUnitCodec = {
        DayBasedDateTimeUnitCodec()
    }

    val fixedOffsetTimeZoneCodec = {
        FixedOffsetTimeZoneCodec()
    }

    val instantCodec = {
        InstantCodec()
    }

    val localDateCodec = {
        LocalDateCodec()
    }

    val localDateTimeCodec = {
        LocalDateTimeCodec()
    }

    val localTimeCodec = {
        LocalTimeCodec()
    }

    val monthBasedDateTimeUnitCodec = {
        MonthBasedDateTimeUnitCodec()
    }

    val timeBasedDateTimeUnitCodec = {
        TimeBasedDateTimeUnitCodec()
    }

    val timeZoneCodec = {
        TimeZoneCodec()
    }

    val utcOffsetCodec = {
        UtcOffsetCodec()
    }

    val all: List<Codec<*>> by lazy {
        generateCodec(
            datePeriodCodec,
            dateTimePeriodCodec,
            dateTimeUnitCodec,
            dayBasedDateTimeUnitCodec,
            fixedOffsetTimeZoneCodec,
            instantCodec,
            localDateCodec,
            localDateTimeCodec,
            localTimeCodec,
            monthBasedDateTimeUnitCodec,
            timeBasedDateTimeUnitCodec,
            timeZoneCodec,
            utcOffsetCodec,
        )
    }

    fun generateCodec(vararg codecs: () -> Codec<*>): List<Codec<*>> {
        return codecs.map {
            it()
        }
    }
}
