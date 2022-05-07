package pl.uz.mt.library.domain.validation

import pl.uz.mt.library.domain.validation.exception.ValidationErrorException

interface ExceptionHandler {

    fun add(exception: ValidationErrorException)
}
