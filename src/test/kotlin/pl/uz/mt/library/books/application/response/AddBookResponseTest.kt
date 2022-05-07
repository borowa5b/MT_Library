package pl.uz.mt.library.books.application.response

import org.junit.jupiter.api.Test

class AddBookResponseTest {

    @Test
    fun shouldCreateResponse() {
        // given
        val bookId = "BK123123123123123"

        // when
        val result = AddBookResponse(bookId)

        // then
        assert(result.id == bookId)
    }
}
