package pl.uz.mt.library.books.infrastructure.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.PageableRepository
import jakarta.inject.Singleton
import pl.uz.mt.library.books.application.dto.BookDto
import pl.uz.mt.library.books.domain.model.BookEntity
import pl.uz.mt.library.books.domain.repository.BookRepository
import java.util.*

@Repository
internal interface JpaBookRepository : PageableRepository<BookEntity, String> {

    fun findByTitle(title: String): Optional<BookEntity>
}

@Singleton
internal class DefaultBookRepository(private val repository: JpaBookRepository) : BookRepository {

    override fun save(bookDto: BookDto): BookEntity = repository.save(BookEntity.fromDto(bookDto))

    override fun update(bookDto: BookDto): BookEntity = repository.update(BookEntity.fromDto(bookDto))

    override fun findById(id: String): BookDto? = repository.findById(id).map { it.toDto() }.orElse(null)

    override fun findByTitle(title: String): BookDto? =
        repository.findByTitle(title).map { it.toDto() }.orElse(null)

    override fun remove(id: String) = repository.deleteById(id)

    override fun findAll(pageable: Pageable): Page<BookDto> = repository.findAll(pageable).map { it.toDto() }
}
