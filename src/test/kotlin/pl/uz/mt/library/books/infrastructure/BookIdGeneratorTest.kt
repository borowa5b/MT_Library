package pl.uz.mt.library.books.infrastructure

import org.junit.jupiter.api.Test
import pl.uz.mt.library.infrastructure.util.DefaultIdGenerator

class BookIdGeneratorTest {

    @Test
    fun shouldGenerateBookId() {
        // given
        val generator = BookIdGenerator(DefaultIdGenerator())
        val bookPrefix = "BK"

        // when
        val result = generator.generate()

        // then
        assert(result.substring(0, 2) == bookPrefix)
    }
}
