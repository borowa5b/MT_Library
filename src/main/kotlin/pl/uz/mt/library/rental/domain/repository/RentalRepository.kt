package pl.uz.mt.library.rental.domain.repository

import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import pl.uz.mt.library.rental.application.dto.RentalDto
import pl.uz.mt.library.rental.domain.model.RentalEntity
import pl.uz.mt.library.rental.domain.model.RentalStatus
import java.time.OffsetDateTime

interface RentalRepository {

    fun save(rentalDto: RentalDto): RentalEntity

    fun update(rentalDto: RentalDto): RentalEntity

    fun findBy(id: String): RentalDto?

    fun findByBookId(bookId: String): RentalDto?

    fun findOverdue(now: OffsetDateTime, inStatus: RentalStatus): List<RentalDto>

    fun findAll(pageable: Pageable): Page<RentalDto>

    fun remove(id: String)
}
