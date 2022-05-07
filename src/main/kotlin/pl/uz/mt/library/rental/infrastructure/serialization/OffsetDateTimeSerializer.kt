package pl.uz.mt.library.rental.infrastructure.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import jakarta.inject.Singleton
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Singleton
class OffsetDateTimeSerializer : JsonSerializer<OffsetDateTime>() {

    override fun serialize(value: OffsetDateTime, gen: JsonGenerator, serializers: SerializerProvider) {
        val utcTime = value.atZoneSameInstant(ZoneOffset.UTC).format(DateTimeFormatter.ISO_DATE_TIME)
        gen.writeString(utcTime)
    }
}
