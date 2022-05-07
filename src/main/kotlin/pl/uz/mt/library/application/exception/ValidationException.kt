package pl.uz.mt.library.application.exception

import io.micronaut.http.HttpStatus
import pl.uz.mt.library.domain.validation.exception.ValidationErrorException

class ValidationException(val errors: ArrayList<ValidationErrorException>) :
    ApplicationException(
        HttpStatus.BAD_REQUEST,
        "bad-request",
        "Parameters didn't passed the validation."
    )
