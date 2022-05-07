package pl.uz.mt.library.rental.application.exception

import io.micronaut.http.HttpStatus
import pl.uz.mt.library.application.exception.ApplicationException

class BookReturnedException(title: String) :
    ApplicationException(
        HttpStatus.CONFLICT,
        "library:book-already-returned",
        "Book '$title' is already returned."
    )
