package pl.uz.mt.library.books.application.exception

import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Test

class BookAlreadyExistsExceptionTest {

    @Test
    fun shouldCreateException() {
        // given
        val bookTitle = "bookTitle"

        // when
        val result = BookAlreadyExistsException(bookTitle)

        // then
        assert(result.httpStatus == HttpStatus.CONFLICT)
        assert(result.type == "library:book-already-exists")
        assert(result.message == "Book with title '$bookTitle' already exists in the library.")
    }
}
