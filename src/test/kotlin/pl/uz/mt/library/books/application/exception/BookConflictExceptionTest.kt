package pl.uz.mt.library.books.application.exception

import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Test

class BookConflictExceptionTest {

    @Test
    fun shouldCreateException() {
        // given
        val bookId = "BK123123123123123"

        // when
        val result = BookConflictException(bookId)

        // then
        assert(result.httpStatus == HttpStatus.CONFLICT)
        assert(result.type == "library:book-conflict")
        assert(result.message == "Book with id '$bookId' can't be removed because it is rented.")
    }
}
