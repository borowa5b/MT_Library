package pl.uz.mt.library.application.factory

import io.micronaut.data.model.Page
import pl.uz.mt.library.application.response.PaginationResponse

object PaginationFactory {

    fun create(page: Page<*>): PaginationResponse =
        PaginationResponse(
            page.pageNumber,
            page.size,
            page.totalSize,
            page.totalPages,
            page.sort.orderBy.joinToString { "${it.property}:${it.direction}" })
}
