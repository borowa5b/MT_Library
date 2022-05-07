package pl.uz.mt.library.books.helpers

import pl.uz.mt.library.books.application.dto.BookDto
import pl.uz.mt.library.books.application.request.AddBookRequest
import pl.uz.mt.library.books.application.response.BookResponse
import pl.uz.mt.library.books.domain.model.BookEntity
import java.time.OffsetDateTime

object BooksObjects {

    fun bookEntity(
        id: String = "BK123123123123",
        title: String = "title",
        description: String = "description",
        author: String = "author",
        year: String = "2022",
        publisher: String = "publisher",
        content: String = "content",
        pages: Int = 100,
        quantity: Int = 100,
        availableQuantity: Int = 100,
        creationDate: OffsetDateTime = OffsetDateTime.now()
    ): BookEntity =
        BookEntity(
            id,
            title,
            description,
            author,
            year,
            publisher,
            content,
            pages,
            quantity,
            availableQuantity,
            creationDate
        )

    fun bookDto(
        id: String = "BK123123123123",
        title: String = "title",
        description: String = "description",
        author: String = "author",
        year: String = "2022",
        publisher: String = "publisher",
        content: String = "content",
        pages: Int = 100,
        quantity: Int = 100,
        availableQuantity: Int = 100,
        creationDate: OffsetDateTime = OffsetDateTime.now()
    ): BookDto = BookDto(
        id,
        title,
        description,
        author,
        year,
        publisher,
        content,
        pages,
        quantity,
        availableQuantity,
        creationDate
    )

    fun addBookRequest(
        title: String = "title",
        description: String = "description",
        author: String = "author",
        year: String = "year",
        publisher: String = "publisher",
        content: String = "content",
        pages: Int = 100,
        quantity: Int = 100
    ): AddBookRequest = AddBookRequest(title, description, author, year, publisher, content, pages, quantity)

    fun bookResponse(
        id: String = "BK123123123123",
        title: String = "title",
        description: String = "description",
        author: String = "author",
        year: String = "2022",
        publisher: String = "publisher",
        pages: Int = 100,
        availableQuantity: Int = 100,
    ): BookResponse = BookResponse(id, title, description, author, publisher, year, pages, availableQuantity)
}
