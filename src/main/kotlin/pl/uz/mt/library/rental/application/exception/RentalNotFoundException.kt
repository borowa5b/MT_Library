package pl.uz.mt.library.rental.application.exception

import io.micronaut.http.HttpStatus
import pl.uz.mt.library.application.exception.ApplicationException

class RentalNotFoundException(id: String) :
    ApplicationException(
        HttpStatus.NOT_FOUND,
        "library:rental-not-found",
        "Rental with id '$id' not found."
    )
