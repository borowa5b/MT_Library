package pl.uz.mt.library.rental.application.response

import pl.uz.mt.library.rental.domain.model.RentalStatus
import java.time.OffsetDateTime

class RentalResponse(
    val id: String,
    val bookId: String,
    val rentedFrom: OffsetDateTime,
    val rentedTo: OffsetDateTime,
    val status: RentalStatus
)
