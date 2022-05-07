package pl.uz.mt.library.books.application.endpoint

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import pl.uz.mt.library.books.application.service.BookService

@Controller("/books")
class RemoveBookEndpoint(private val bookService: BookService) {

    @Operation(summary = "Remove a book")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Book removed"),
        ApiResponse(responseCode = "404", description = "Book not found"),
        ApiResponse(responseCode = "503", description = "Service unavailable")
    )
    @Tag(name = "books")
    @Delete(value = "/{bookId}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun removeBook(bookId: String): HttpResponse<String> {
        bookService.removeBook(bookId)
        return HttpResponse.ok()
    }
}
