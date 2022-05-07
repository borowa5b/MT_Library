package pl.uz.mt.library.rental.domain.repository

import pl.uz.mt.library.rental.application.dto.RentedBookDto

interface RentedBookRepository {

    fun findBy(bookId: String): RentedBookDto?
}
