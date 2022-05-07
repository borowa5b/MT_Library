package pl.uz.mt.library.rental.application.endpoint

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import pl.uz.mt.library.rental.application.response.GetRentedBookResponse
import pl.uz.mt.library.rental.application.service.RentalService

@Controller("/rentals")
class GetRentedBookEndpoint(private val rentalService: RentalService) {

    @Operation(summary = "Gets rented book")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Rented book fetched"),
        ApiResponse(responseCode = "404", description = "Not found"),
        ApiResponse(responseCode = "409", description = "Rented book overdue"),
        ApiResponse(responseCode = "503", description = "Service unavailable")
    )
    @Tag(name = "rentals")
    @Get(value = "/{id}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun getRentals(id: String): GetRentedBookResponse {
        val rentedBook = rentalService.getRentedBook(id)
        return GetRentedBookResponse(rentedBook.id, rentedBook.title, rentedBook.content)
    }
}
