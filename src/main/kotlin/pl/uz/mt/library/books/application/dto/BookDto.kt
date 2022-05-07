package pl.uz.mt.library.books.application.dto

import pl.uz.mt.library.domain.validation.ExceptionHandler
import pl.uz.mt.library.domain.validation.ThrowingExceptionHandler
import pl.uz.mt.library.domain.validation.Validator
import java.time.OffsetDateTime

data class BookDto(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val year: String,
    val publisher: String,
    val content: String,
    val pages: Int,
    val quantity: Int,
    var availableQuantity: Int,
    val creationDate: OffsetDateTime = OffsetDateTime.now()
) {

    init {
        validate(title, description, author, year, publisher, content, pages, quantity)
    }

    companion object {
        fun validate(
            title: String?,
            description: String?,
            author: String?,
            year: String?,
            publisher: String?,
            content: String?,
            pages: Int?,
            quantity: Int?,
            exceptionHandler: ExceptionHandler = ThrowingExceptionHandler()
        ) {
            Validator.isNotNullOrBlank(title, "title", exceptionHandler)
            Validator.isNotNullOrBlank(description, "description", exceptionHandler)
            Validator.isNotNullOrBlank(author, "author", exceptionHandler)
            Validator.isNotNullOrBlank(year, "year", exceptionHandler)
            Validator.isNotNullOrBlank(publisher, "publisher", exceptionHandler)
            Validator.isNotNullOrBlank(content, "content", exceptionHandler)
            pages?.let { Validator.isGreaterThan(it, "pages", 1, exceptionHandler) }
            quantity?.let { Validator.isGreaterOrEqualThan(it, "quantity", 1, exceptionHandler) }
        }
    }
}
