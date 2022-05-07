package pl.uz.mt.library.books.domain.repository

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import pl.uz.mt.library.books.application.dto.BookDto
import pl.uz.mt.library.books.domain.model.BookEntity

interface BookRepository {

    fun save(bookDto: BookDto): BookEntity

    fun update(bookDto: BookDto): BookEntity

    fun findById(id: String): BookDto?

    fun findByTitle(title: String): BookDto?

    fun remove(id: String)

    fun findAll(pageable: Pageable): Page<BookDto>
}
