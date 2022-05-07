package pl.uz.mt.library.books.application.request

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.uz.mt.library.application.exception.ValidationException

class AddBookRequestTest {

    @Test
    fun shouldCreateAddBookRequest() {
        // given
        val title = "title"
        val description = "description"
        val author = "author"
        val year = "year"
        val publisher = "publisher"
        val content = "content"
        val pages = 100
        val quantity = 100

        // when
        val result = AddBookRequest(title, description, author, year, publisher, content, pages, quantity)

        // then
        assert(result.title == title)
        assert(result.description == description)
        assert(result.author == author)
        assert(result.year == year)
        assert(result.publisher == publisher)
        assert(result.content == content)
        assert(result.pages == pages)
        assert(result.quantity == quantity)
    }

    @Test
    fun shouldValidateAddBookRequest() {
        // given
        val title = "title"
        val description = "description"
        val author = "author"
        val year = "year"
        val publisher = "publisher"
        val content = "content"
        val pages = 0
        val quantity = 100
        val addBookRequest = AddBookRequest(title, description, author, year, publisher, content, pages, quantity)

        // when
        val result = assertThrows<ValidationException> { addBookRequest.validate() }

        // then
        assert(result.errors.size == 1)
    }
}
