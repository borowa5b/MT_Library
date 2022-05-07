package pl.uz.mt.library.rental.domain.event

import pl.uz.mt.library.domain.DomainEvent

data class BookRentalRequestedEvent(val bookId: String) : DomainEvent
