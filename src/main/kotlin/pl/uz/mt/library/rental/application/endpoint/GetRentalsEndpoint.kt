package pl.uz.mt.library.rental.application.endpoint

import io.micronaut.data.model.Sort
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
import pl.uz.mt.library.rental.application.response.GetRentalsResponse
import pl.uz.mt.library.rental.application.response.RentalResponse
import pl.uz.mt.library.rental.application.service.RentalService

@Controller("/rentals")
class GetRentalsEndpoint(private val rentalService: RentalService) {

    @Operation(summary = "Gets rentals")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            content = [Content(
                mediaType = MediaType.APPLICATION_JSON,
                schema = Schema(implementation = GetRentalsResponse::class),
            )], description = "Rentals fetched"
        ),
        ApiResponse(responseCode = "400", description = "Bad request"),
        ApiResponse(responseCode = "503", description = "Service unavailable")
    )
    @Tag(name = "rentals")
    @Get(value = "{?pageFilter*}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun getRentals(pageFilter: PageFilter): GetRentalsResponse {
        pageFilter.sort = Sort.of(Sort.Order.desc("rentedFrom"))
        val rentals = rentalService.getRentals(pageFilter)
        return GetRentalsResponse(rentals.content.map {
            RentalResponse(
                it.id,
                it.book.id,
                it.rentedFrom,
                it.rentedTo,
                it.status
            )
        }, PaginationFactory.create(rentals))
    }
}
