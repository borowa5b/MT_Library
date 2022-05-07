package pl.uz.mt.library.books.application.endpoint

import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import pl.uz.mt.library.books.application.service.BookService

class RemoveBookEndpointTest {

    private val bookService = mock(BookService::class.java)

    @Test
    fun shouldRemoveBook() {
        // given
        val endpoint = RemoveBookEndpoint(bookService)
        val bookId = "BK123123123"

        // when
        val result = endpoint.removeBook(bookId)


        // then
        assert(result.status == HttpStatus.OK)
    }
}
