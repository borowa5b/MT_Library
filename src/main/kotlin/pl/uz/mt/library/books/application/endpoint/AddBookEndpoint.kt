package pl.uz.mt.library.books.application.endpoint

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import pl.uz.mt.library.books.application.request.AddBookRequest
import pl.uz.mt.library.books.application.response.AddBookResponse
import pl.uz.mt.library.books.application.service.BookService

@Controller("/books")
class AddBookEndpoint(private val bookService: BookService) {

    @Operation(summary = "Adds a book")
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = Schema(implementation = AddBookResponse::class)
            )],
            description = "Book added"
        ),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ApiResponse(responseCode = "409", description = "Book already exists"),
        ApiResponse(responseCode = "503", description = "Service unavailable")
    )
    @Tag(name = "books")
    @Post(consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun addBook(@Body request: AddBookRequest): HttpResponse<AddBookResponse> {
        request.validate()
        val addedBookId = bookService.addBook(request.toCommand())
        return HttpResponse.created(AddBookResponse(addedBookId))
    }
}
