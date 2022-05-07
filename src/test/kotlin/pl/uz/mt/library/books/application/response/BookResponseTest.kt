package pl.uz.mt.library.books.application.response

import org.junit.jupiter.api.Test

class BookResponseTest {

    @Test
    fun shouldCreateResponse() {
        // given
        val id = "BK123123123123"
        val title = "title"
        val description = "description"
        val author = "author"
        val year = "2022"
        val publisher = "publisher"
        val pages = 100
        val availableQuantity = 100

        // when
        val result = BookResponse(id, title, description, author, publisher, year, pages, availableQuantity)

        // then
        assert(result.id == id)
        assert(result.title == title)
        assert(result.description == description)
        assert(result.author == author)
        assert(result.year == year)
        assert(result.publisher == publisher)
        assert(result.pages == pages)
        assert(result.availableQuantity == availableQuantity)
    }
}
