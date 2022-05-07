package pl.uz.mt.library.rental.application.endpoint

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import pl.uz.mt.library.rental.application.response.RentBookResponse
import pl.uz.mt.library.rental.application.service.RentalService

@Controller("/rentals")
class RentBookEndpoint(private val rentalService: RentalService) {

    @Operation(summary = "Rent a book")
    @ApiResponses(
        ApiResponse(
            responseCode = "201",
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = Schema(implementation = RentBookResponse::class)
            )],
            description = "Book rented"
        ),
        ApiResponse(responseCode = "404", description = "Book not found"),
        ApiResponse(responseCode = "409", description = "Book out of stock"),
        ApiResponse(responseCode = "503", description = "Service unavailable")
    )
    @Tag(name = "rentals")
    @Post(value = "/{bookId}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun rentBook(bookId: String): HttpResponse<RentBookResponse> {
        val addedRentalId = rentalService.rentBook(bookId)
        return HttpResponse.created(RentBookResponse(addedRentalId))
    }

}
