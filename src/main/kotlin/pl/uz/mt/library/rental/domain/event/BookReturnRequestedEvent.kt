package pl.uz.mt.library.rental.domain.event

import pl.uz.mt.library.domain.DomainEvent

data class BookReturnRequestedEvent(val bookId: String) : DomainEvent
