package pl.uz.mt.library.books.infrastructure

import jakarta.inject.Singleton
import pl.uz.mt.library.domain.IdGenerator

@Singleton
class BookIdGenerator(private val idGenerator: IdGenerator) {

    companion object {
        private const val BOOK_PREFIX = "BK"
    }

    fun generate(): String = idGenerator.generate(BOOK_PREFIX)
}
