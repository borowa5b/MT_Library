package pl.uz.mt.library.rental.infrastructure.repository

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.data.repository.PageableRepository
import jakarta.inject.Singleton
import pl.uz.mt.library.rental.application.dto.RentalDto
import pl.uz.mt.library.rental.domain.model.RentalEntity
import pl.uz.mt.library.rental.domain.model.RentalStatus
import pl.uz.mt.library.rental.domain.repository.RentalRepository
import java.time.OffsetDateTime

@Repository
internal interface JpaRentalRepository : PageableRepository<RentalEntity, String> {

    fun findByRentedToLessThanAndStatus(now: OffsetDateTime, inStatus: RentalStatus): List<RentalEntity>

    @Query("SELECT re FROM RentalEntity re WHERE re.book.id = :bookId")
    fun findByBookId(bookId: String): RentalEntity?
}

@Singleton
internal class DefaultRentalRepository(private val repository: JpaRentalRepository) : RentalRepository {

    override fun save(rentalDto: RentalDto): RentalEntity = repository.save(RentalEntity.fromDto(rentalDto))

    override fun update(rentalDto: RentalDto): RentalEntity = repository.update(RentalEntity.fromDto(rentalDto))

    override fun findBy(id: String): RentalDto? = repository.findById(id).map { it.toDto() }.orElse(null)

    override fun findByBookId(bookId: String): RentalDto? = repository.findByBookId(bookId)?.toDto()

    override fun findOverdue(now: OffsetDateTime, inStatus: RentalStatus): List<RentalDto> =
        repository.findByRentedToLessThanAndStatus(now, inStatus).map { it.toDto() }

    override fun findAll(pageable: Pageable): Page<RentalDto> = repository.findAll(pageable).map { it.toDto() }

    override fun remove(id: String) = repository.deleteById(id)
}
