package pl.uz.mt.library.books.application.dto

import org.junit.jupiter.api.Test
import pl.uz.mt.library.domain.validation.AggregatingExceptionHandler
import java.time.OffsetDateTime

class BookDtoTest {

    @Test
    fun shouldCreateBookDto() {
        // given
        val id = "BK123123123123"
        val title = "title"
        val description = "description"
        val author = "author"
        val year = "2022"
        val publisher = "publisher"
        val content = "content"
        val pages = 100
        val quantity = 100
        val creationDate = OffsetDateTime.now()

        // when
        val result = BookDto(
            id,
            title,
            description,
            author,
            year,
            publisher,
            content,
            pages,
            quantity,
            quantity,
            creationDate
        )

        // then
        assert(result.id == id)
        assert(result.title == title)
        assert(result.description == description)
        assert(result.author == author)
        assert(result.year == year)
        assert(result.publisher == publisher)
        assert(result.content == content)
        assert(result.pages == pages)
        assert(result.quantity == quantity)
        assert(result.availableQuantity == quantity)
        assert(result.creationDate == creationDate)
    }

    @Test
    fun shouldValidateBookDto() {
        // given
        val title = "title"
        val description = "description"
        val author = "author"
        val year = "2022"
        val publisher = "publisher"
        val content = "content"
        val pages = 100
        val quantity = 100
        val exceptionHandler = AggregatingExceptionHandler()

        // when
        BookDto.validate(title, description, author, year, publisher, content, pages, quantity, exceptionHandler)

        // then
        assert(!exceptionHandler.hasExceptions())
    }

    @Test
    fun shouldNotValidateBookDto() {
        // given
        val title = ""
        val description = ""
        val author = ""
        val year = ""
        val publisher = ""
        val content = ""
        val pages = 1
        val quantity = 0
        val exceptionHandler = AggregatingExceptionHandler()

        // when
        BookDto.validate(title, description, author, year, publisher, content, pages, quantity, exceptionHandler)

        // then
        assert(exceptionHandler.hasExceptions())

        val exceptionErrors = exceptionHandler.exceptionErrors
        assert(exceptionErrors.size == 8)
    }
}
