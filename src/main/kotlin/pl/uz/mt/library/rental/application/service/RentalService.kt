package pl.uz.mt.library.rental.application.service

import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.inject.Singleton
import pl.uz.mt.library.application.exception.BookNotFoundException
import pl.uz.mt.library.application.filter.PageFilter
import pl.uz.mt.library.books.application.exception.BookConflictException
import pl.uz.mt.library.domain.DomainEvent
import pl.uz.mt.library.rental.application.dto.RentalDto
import pl.uz.mt.library.rental.application.dto.RentedBookDto
import pl.uz.mt.library.rental.application.exception.BookReturnedException
import pl.uz.mt.library.rental.application.exception.RentalNotFoundException
import pl.uz.mt.library.rental.application.exception.RentalOverdueException
import pl.uz.mt.library.rental.domain.event.BookRentalRequestedEvent
import pl.uz.mt.library.rental.domain.event.BookReturnRequestedEvent
import pl.uz.mt.library.rental.domain.model.RentalStatus
import pl.uz.mt.library.rental.domain.repository.RentalRepository
import pl.uz.mt.library.rental.domain.repository.RentedBookRepository
import pl.uz.mt.library.rental.infrastructure.RentalIdGenerator
import java.time.OffsetDateTime
import java.util.logging.Logger
import javax.transaction.Transactional

private val LOG: Logger = Logger.getLogger(RentalService::class.java.name)

@Singleton
open class RentalService(
    private val rentalRepository: RentalRepository,
    private val rentedBookRepository: RentedBookRepository,
    private val rentalIdGenerator: RentalIdGenerator,
    private val applicationEventPublisher: ApplicationEventPublisher<DomainEvent>
) {

    @Transactional
    open fun rentBook(bookId: String): String {
        val bookToRent = rentedBookRepository.findBy(bookId) ?: throw BookNotFoundException(bookId)
        val rentedFromDate = OffsetDateTime.now()
        val rentalDto = RentalDto(
            rentalIdGenerator.generate(), bookToRent, rentedFromDate, rentedFromDate.plusDays(7), RentalStatus.RENTED
        )

        applicationEventPublisher.publishEvent(BookRentalRequestedEvent(bookId))
        return rentalRepository.save(rentalDto).id
    }

    @Transactional
    open fun returnBook(id: String) {
        val rental = rentalRepository.findBy(id) ?: throw RentalNotFoundException(id)
        if (rental.status == RentalStatus.RETURNED) throw BookReturnedException(rental.book.title)

        rental.status = RentalStatus.RETURNED
        applicationEventPublisher.publishEvent(BookReturnRequestedEvent(rental.book.id))
        rentalRepository.update(rental)
    }

    @Transactional
    open fun overdueRentals() {
        val overdueRentals = rentalRepository.findOverdue(OffsetDateTime.now(), RentalStatus.RENTED)
        overdueRentals.forEach {
            LOG.info("Changing book status with identifier ${it.book.id} because of overdue.")
            it.status = RentalStatus.OVERDUE
            rentalRepository.update(it)
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    open fun validateRemoval(bookId: String) {
        val rental = rentalRepository.findByBookId(bookId)
        rental?.let {
            if (rental.status == RentalStatus.RENTED) throw BookConflictException(bookId)
            rentalRepository.remove(rental.id)
        }
    }

    fun getRentals(filter: PageFilter): Page<RentalDto> {
        val pageable = Pageable.from(filter.pageNumber, filter.pageSize, filter.sort)
        return rentalRepository.findAll(pageable)
    }

    fun getRentedBook(id: String): RentedBookDto {
        val rental = rentalRepository.findBy(id) ?: throw RentalNotFoundException(id)
        val rentedBook = rentedBookRepository.findBy(rental.book.id)!!
        val rentedBookTitle = rentedBook.title
        if (rental.status == RentalStatus.RETURNED) throw BookReturnedException(rentedBookTitle)
        if (rental.status == RentalStatus.OVERDUE) throw RentalOverdueException(rentedBookTitle)

        return rentedBook
    }
}
