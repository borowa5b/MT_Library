package pl.uz.mt.library.rental.application.dto

import pl.uz.mt.library.rental.domain.model.RentalStatus
import java.time.OffsetDateTime

data class RentalDto(
    val id: String,
    val book: RentedBookDto,
    val rentedFrom: OffsetDateTime,
    val rentedTo: OffsetDateTime,
    var status: RentalStatus
)
