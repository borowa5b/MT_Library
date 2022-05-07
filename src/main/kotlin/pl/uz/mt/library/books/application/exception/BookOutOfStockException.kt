package pl.uz.mt.library.books.application.exception

import io.micronaut.http.HttpStatus
import pl.uz.mt.library.application.exception.ApplicationException

class BookOutOfStockException(title: String) :
    ApplicationException(
        HttpStatus.CONFLICT,
        "library:book-out-of-stock",
        "Book '$title' is out of stock and can't be rented."
    )
