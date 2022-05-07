package pl.uz.mt.library.books.application.exception

import io.micronaut.http.HttpStatus
import pl.uz.mt.library.application.exception.ApplicationException

class BookConflictException(id: String) :
    ApplicationException(
        HttpStatus.CONFLICT,
        "library:book-conflict",
        "Book with id '$id' can't be removed because it is rented."
    )
