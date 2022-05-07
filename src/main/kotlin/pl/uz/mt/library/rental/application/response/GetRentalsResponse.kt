package pl.uz.mt.library.rental.application.response

import pl.uz.mt.library.application.response.PageResponse
import pl.uz.mt.library.application.response.PaginationResponse

class GetRentalsResponse(override val data: List<RentalResponse>, override val pagination: PaginationResponse) :
    PageResponse<RentalResponse>(data, pagination)
