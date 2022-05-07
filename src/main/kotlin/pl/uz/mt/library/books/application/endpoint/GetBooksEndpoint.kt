package pl.uz.mt.library.books.application.endpoint

import io.micronaut.data.model.Sort
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import pl.uz.mt.library.application.factory.PaginationFactory
import pl.uz.mt.library.application.filter.PageFilter
import pl.uz.mt.library.books.application.response.BookResponse
import pl.uz.mt.library.books.application.response.GetBooksResponse
import pl.uz.mt.library.books.application.service.BookService

@Controller("/books")
class GetBooksEndpoint(private val bookService: BookService) {

    @Operation(summary = "Gets books")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = Schema(implementation = GetBooksResponse::class),
            )], description = "Books fetched"
        ),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ApiResponse(responseCode = "503", description = "Service unavailable")
    )
    @Tag(name = "books")
    @Get(value = "{?pageFilter*}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun getBooks(pageFilter: PageFilter): HttpResponse<GetBooksResponse> {
        pageFilter.sort = Sort.of(Sort.Order.desc("creationDate"))
        val books = bookService.getBooks(pageFilter)
        return HttpResponse.ok<GetBooksResponse?>().body(GetBooksResponse(books.content.map {
            BookResponse(
                it.id,
                it.title,
                it.description,
                it.author,
                it.publisher,
                it.year,
                it.pages,
                it.availableQuantity
            )
        }, PaginationFactory.create(books)))
    }
}
