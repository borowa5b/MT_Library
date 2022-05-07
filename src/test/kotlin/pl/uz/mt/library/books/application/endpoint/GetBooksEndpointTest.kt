package pl.uz.mt.library.books.application.endpoint

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpStatus
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import pl.uz.mt.library.books.application.service.BookService
import pl.uz.mt.library.books.helpers.BooksObjects.bookDto
import pl.uz.mt.library.helpers.TestObjects.pageFilter

class GetBooksEndpointTest {

    private val bookService = mock(BookService::class.java)

    @Test
    fun shouldGetBooks() {
        // given
        val filter = pageFilter()
        val endpoint = GetBooksEndpoint(bookService)
        val expectedResponse = Page.of(listOf(bookDto(), bookDto()), Pageable.UNPAGED, 2);
        `when`(bookService.getBooks(any())).thenReturn(expectedResponse)

        // when
        val result = endpoint.getBooks(filter)


        // then
        assert(result.status == HttpStatus.OK)
        assert(result.body.get().data.size == expectedResponse.totalSize.toInt())
    }
}
