package pl.uz.mt.library.books.domain.model

import org.junit.jupiter.api.Test
import pl.uz.mt.library.books.helpers.BooksObjects.bookDto
import pl.uz.mt.library.books.helpers.BooksObjects.bookEntity
import java.time.OffsetDateTime

class BookEntityTest {

    @Test
    fun shouldCreateEntity() {
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
        val result = BookEntity(
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
    fun shouldConvertToDto() {
        // given
        val bookEntity = bookEntity()

        // when
        val result = bookEntity.toDto()

        // then
        assert(result.id == bookEntity.id)
        assert(result.title == bookEntity.title)
        assert(result.description == bookEntity.description)
        assert(result.author == bookEntity.author)
        assert(result.year == bookEntity.year)
        assert(result.publisher == bookEntity.publisher)
        assert(result.content == bookEntity.content)
        assert(result.pages == bookEntity.pages)
        assert(result.quantity == bookEntity.quantity)
        assert(result.availableQuantity == bookEntity.availableQuantity)
        assert(result.creationDate == bookEntity.creationDate)
    }

    @Test
    fun shouldConvertFromDto() {
        // given
        val bookDto = bookDto()

        // when
        val result = BookEntity.fromDto(bookDto)

        // then
        assert(result.id == bookDto.id)
        assert(result.title == bookDto.title)
        assert(result.description == bookDto.description)
        assert(result.author == bookDto.author)
        assert(result.year == bookDto.year)
        assert(result.publisher == bookDto.publisher)
        assert(result.content == bookDto.content)
        assert(result.pages == bookDto.pages)
        assert(result.quantity == bookDto.quantity)
        assert(result.availableQuantity == bookDto.availableQuantity)
        assert(result.creationDate == bookDto.creationDate)

    }
}
