package pl.uz.mt.library.books.application.request

import pl.uz.mt.library.application.exception.ValidationException
import pl.uz.mt.library.books.application.dto.BookDto
import pl.uz.mt.library.domain.validation.AggregatingExceptionHandler

data class AddBookRequest(
    val title: String?,
    val description: String?,
    val author: String?,
    val year: String?,
    val publisher: String?,
    val content: String?,
    val pages: Int?,
    val quantity: Int?
) {
    fun validate() {
        val exceptionHandler = AggregatingExceptionHandler()
        BookDto.validate(title, description, author, year, publisher, content, pages, quantity, exceptionHandler)
        if (exceptionHandler.hasExceptions()) {
            throw ValidationException(exceptionHandler.exceptionErrors)
        }
    }
}
