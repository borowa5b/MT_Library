package pl.uz.mt.library.helpers

import io.micronaut.data.model.Sort
import pl.uz.mt.library.application.filter.PageFilter
import pl.uz.mt.library.application.response.PaginationResponse

object TestObjects {

    fun pageFilter(
        pageNumber: Int = 1,
        pageSize: Int = 10,
        sort: Sort? = null
    ): PageFilter = PageFilter(pageNumber, pageSize, sort)

    fun paginationResponse(
        pageNumber: Int = 1,
        pageSize: Int = 10,
        totalElements: Long = 1,
        totalPages: Int = 1,
        sort: String = ""
    ): PaginationResponse = PaginationResponse(pageNumber, pageSize, totalElements, totalPages, sort)
}
