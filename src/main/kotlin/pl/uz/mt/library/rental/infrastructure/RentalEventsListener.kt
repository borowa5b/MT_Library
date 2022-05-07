package pl.uz.mt.library.rental.infrastructure

import io.micronaut.context.event.ApplicationEventListener
import jakarta.inject.Singleton
import pl.uz.mt.library.books.domain.event.BookRemovedEvent
import pl.uz.mt.library.domain.DomainEvent
import pl.uz.mt.library.rental.application.service.RentalService

@Singleton
open class RentalEventsListener(private val rentalService: RentalService) : ApplicationEventListener<DomainEvent> {

    override fun onApplicationEvent(event: DomainEvent?) {
        when (event) {
            is BookRemovedEvent -> handle(event)
        }
    }

    private fun handle(event: BookRemovedEvent) {
        val bookId = event.bookId
        rentalService.validateRemoval(bookId)
    }
}
