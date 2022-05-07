package pl.uz.mt.library.application.filter

import io.micronaut.data.model.Sort
import pl.uz.mt.library.application.exception.ValidationException
import pl.uz.mt.library.domain.validation.AggregatingExceptionHandler
import pl.uz.mt.library.domain.validation.Validator

private const val DEFAULT_PAGE_NUMBER = 0
private const val DEFAULT_PAGE_SIZE = 50

class PageFilter(
    val pageNumber: Int = DEFAULT_PAGE_NUMBER,
    val pageSize: Int = DEFAULT_PAGE_SIZE,
    var sort: Sort? = null
) {

    init {
        validate()
    }

    private fun validate() {
        val exceptionHandler = AggregatingExceptionHandler()

        Validator.isGreaterOrEqualThan(pageNumber, "pageNumber", 0, exceptionHandler)
        Validator.isGreaterThan(pageSize, "pageSize", 0, exceptionHandler)

        if (exceptionHandler.hasExceptions()) {
            throw ValidationException(exceptionHandler.exceptionErrors)
        }
    }
}
