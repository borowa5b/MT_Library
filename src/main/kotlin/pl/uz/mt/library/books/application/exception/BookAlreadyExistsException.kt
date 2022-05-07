package pl.uz.mt.library.books.application.exception

import io.micronaut.http.HttpStatus
import pl.uz.mt.library.application.exception.ApplicationException

class BookAlreadyExistsException(title: String) :
    ApplicationException(
        HttpStatus.CONFLICT,
        "library:book-already-exists",
        "Book with title '$title' already exists in the library."
    )
