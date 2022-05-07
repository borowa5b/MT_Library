package pl.uz.mt.library.books.application.response

import org.junit.jupiter.api.Test
import pl.uz.mt.library.books.helpers.BooksObjects.bookResponse
import pl.uz.mt.library.helpers.TestObjects.paginationResponse

class GetBooksResponseTest {

    @Test
    fun shouldCreateResponse() {
        // given
        val books = listOf(bookResponse())
        val paginationResponse = paginationResponse()

        // when
        val result = GetBooksResponse(books, paginationResponse)

        // then
        assert(result.data.containsAll(books))
    }
}
