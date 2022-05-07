package pl.uz.mt.library.books.domain.event

import pl.uz.mt.library.domain.DomainEvent

data class BookRemovedEvent(val bookId: String) : DomainEvent
