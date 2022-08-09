package pl.uz.mt.library.infrastructure.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import jakarta.inject.Singleton
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Singleton
class OffsetDateTimeDeserializer : JsonDeserializer<OffsetDateTime>() {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): OffsetDateTime {
        val value = p.valueAsString
        try {
            return OffsetDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME)
        } catch (dateTimeParseException: DateTimeParseException) {
            throw dateTimeParseException
        }
    }
}
