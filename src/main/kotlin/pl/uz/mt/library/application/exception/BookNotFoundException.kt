package pl.uz.mt.library.application.exception

import io.micronaut.http.HttpStatus
import pl.uz.mt.library.application.exception.ApplicationException

class BookNotFoundException(id: String) :
    ApplicationException(
        HttpStatus.NOT_FOUND,
        "library:book-not-found",
        "Book with id '$id' not found."
    )
