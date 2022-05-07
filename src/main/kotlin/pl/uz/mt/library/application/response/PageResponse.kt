package pl.uz.mt.library.application.response

open class PageResponse<T>(
    open val data: List<T>,
    open val pagination: PaginationResponse
)

class PaginationResponse(
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Long,
    val totalPages: Int,
    val sort: String
)
