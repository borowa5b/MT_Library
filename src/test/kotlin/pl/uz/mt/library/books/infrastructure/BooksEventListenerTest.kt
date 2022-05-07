package pl.uz.mt.library.books.infrastructure

import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import pl.uz.mt.library.books.application.service.BookService
import pl.uz.mt.library.rental.domain.event.BookRentalRequestedEvent
import pl.uz.mt.library.rental.domain.event.BookReturnRequestedEvent

class BooksEventListenerTest {

    private val bookService: BookService = mock(BookService::class.java)
    private val booksEventListener: BooksEventsListener = BooksEventsListener(bookService)

    @Test
    fun shouldHandleBookRentalRequestedEvent() {
        // given
        val bookId = "BK123123123123123"
        val event = BookRentalRequestedEvent(bookId)

        // when
        booksEventListener.onApplicationEvent(event)


        // then
        verify(bookService).rentBook(bookId)
    }

    @Test
    fun shouldHandleBookReturnRequestedEvent() {
        // given
        val bookId = "BK123123123123123"
        val event = BookReturnRequestedEvent(bookId)

        // when
        booksEventListener.onApplicationEvent(event)

        // then
        verify(bookService).returnBook(bookId)
    }
}
