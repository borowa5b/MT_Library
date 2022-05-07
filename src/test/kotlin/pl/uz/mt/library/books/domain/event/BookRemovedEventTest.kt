package pl.uz.mt.library.books.domain.event

import org.junit.jupiter.api.Test

class BookRemovedEventTest {

    @Test
    fun shouldCreateEvent() {
        // given
        val bookId = "BK1231231233123"

        // when
        val result = BookRemovedEvent(bookId)

        // then
        assert(result.bookId == bookId)
    }
}
