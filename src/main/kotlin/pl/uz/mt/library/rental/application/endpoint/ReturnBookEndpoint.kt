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
class ReturnBookEndpoint(private val rentalService: RentalService) {

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
        ApiResponse(responseCode = "404", description = "Not found"),
        ApiResponse(responseCode = "409", description = "Conflict"),
        ApiResponse(responseCode = "503", description = "Service unavailable")
    )
    @Tag(name = "rentals")
    @Post(value = "/{id}/return", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun returnBook(id: String): HttpResponse<RentBookResponse> {
        rentalService.returnBook(id)
        return HttpResponse.ok()
    }

}
