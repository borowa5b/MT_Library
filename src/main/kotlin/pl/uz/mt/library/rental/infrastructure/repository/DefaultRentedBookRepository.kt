package pl.uz.mt.library.rental.infrastructure.repository

import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository
import jakarta.inject.Singleton
import pl.uz.mt.library.rental.application.dto.RentedBookDto
import pl.uz.mt.library.rental.domain.model.RentedBookEntity
import pl.uz.mt.library.rental.domain.repository.RentedBookRepository


@Repository
internal interface JpaRentedBookRepository : CrudRepository<RentedBookEntity, String>

@Singleton
internal class DefaultRentedBookRepository(private val repository: JpaRentedBookRepository) : RentedBookRepository {

    override fun findBy(bookId: String): RentedBookDto? = repository.findById(bookId).map { it.toDto() }.orElse(null)
}
