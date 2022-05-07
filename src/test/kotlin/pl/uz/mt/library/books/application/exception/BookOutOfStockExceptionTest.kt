package pl.uz.mt.library.books.application.exception

import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Test

class BookOutOfStockExceptionTest {

    @Test
    fun shouldCreateException() {
        // given
        val bookTitle = "bookTitle"

        // when
        val result = BookOutOfStockException(bookTitle)

        // then
        assert(result.httpStatus == HttpStatus.CONFLICT)
        assert(result.type == "library:book-out-of-stock")
        assert(result.message == "Book '$bookTitle' is out of stock and can't be rented.")
    }
}
