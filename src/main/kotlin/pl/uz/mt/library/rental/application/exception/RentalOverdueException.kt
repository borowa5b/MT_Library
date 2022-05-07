package pl.uz.mt.library.rental.application.exception

import io.micronaut.http.HttpStatus
import pl.uz.mt.library.application.exception.ApplicationException

class RentalOverdueException(title: String) :
    ApplicationException(
        HttpStatus.CONFLICT,
        "library:rental-overdue",
        "Rental of the book '$title' is overdue. Please return book back to the library."
    )
