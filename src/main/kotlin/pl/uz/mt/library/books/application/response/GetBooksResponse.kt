package pl.uz.mt.library.books.application.response

import pl.uz.mt.library.application.response.PageResponse
import pl.uz.mt.library.application.response.PaginationResponse

class GetBooksResponse(override val data: List<BookResponse>, override val pagination: PaginationResponse) :
    PageResponse<BookResponse>(data, pagination)
