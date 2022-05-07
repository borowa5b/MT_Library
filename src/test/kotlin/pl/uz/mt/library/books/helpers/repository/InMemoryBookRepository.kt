package pl.uz.mt.library.books.helpers.repository

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import pl.uz.mt.library.books.application.dto.BookDto
import pl.uz.mt.library.books.domain.model.BookEntity
import pl.uz.mt.library.books.domain.repository.BookRepository

class InMemoryBookRepository : BookRepository {

    private val books = ArrayList<BookEntity>()

    override fun save(bookDto: BookDto): BookEntity {
        val bookEntity = BookEntity.fromDto(bookDto)
        books.add(bookEntity)
        return bookEntity
    }

    override fun update(bookDto: BookDto): BookEntity {
        val bookEntity = BookEntity.fromDto(bookDto)
        if (books.contains(bookEntity)) books.remove(bookEntity)
        books.add(bookEntity)
        return bookEntity
    }

    override fun findById(id: String): BookDto? = books.find { bookEntity -> bookEntity.id == id }?.toDto()

    override fun findByTitle(title: String): BookDto? = books.find { bookEntity -> bookEntity.title == title }?.toDto()

    override fun remove(id: String) {
        val bookToRemove = books.find { bookEntity -> bookEntity.id == id }
        books.remove(bookToRemove)
    }

    override fun findAll(pageable: Pageable): Page<BookDto> =
        Page.of(books.map { it.toDto() }, Pageable.UNPAGED, books.size.toLong())

    fun clear() = books.clear()
}
