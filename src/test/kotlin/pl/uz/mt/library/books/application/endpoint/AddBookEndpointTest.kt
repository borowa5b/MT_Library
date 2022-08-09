package pl.uz.mt.library.books.application.endpoint

import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import pl.uz.mt.library.books.application.service.BookService
import pl.uz.mt.library.books.helpers.BooksObjects.addBookRequest

class AddBookEndpointTest {

    private val bookService = mock(BookService::class.java)

    @Test
    fun shouldAddBook() {
        // given
        val request = addBookRequest()
        val endpoint = AddBookEndpoint(bookService)
        val addedBookId = "BK123123123"
        `when`(bookService.addBook(any())).thenReturn(addedBookId)

        // when
        val result = endpoint.addBook(request)


        // then
        assert(result.status == HttpStatus.CREATED)
        assert(result.body.get().id == addedBookId)
    }
}
