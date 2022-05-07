package pl.uz.mt.library.books.infrastructure

import io.micronaut.context.event.ApplicationEventListener
import jakarta.inject.Singleton
import pl.uz.mt.library.books.application.service.BookService
import pl.uz.mt.library.domain.DomainEvent
import pl.uz.mt.library.rental.domain.event.BookRentalRequestedEvent
import pl.uz.mt.library.rental.domain.event.BookReturnRequestedEvent

@Singleton
class BooksEventsListener(private val bookService: BookService) : ApplicationEventListener<DomainEvent> {

    override fun onApplicationEvent(event: DomainEvent) {
        when (event) {
            is BookRentalRequestedEvent -> handle(event)
            is BookReturnRequestedEvent -> handle(event)
        }
    }

    private fun handle(event: BookRentalRequestedEvent) {
        val bookId = event.bookId
        bookService.rentBook(bookId)
    }

    private fun handle(event: BookReturnRequestedEvent) {
        val bookId = event.bookId
        bookService.returnBook(bookId)
    }
}
