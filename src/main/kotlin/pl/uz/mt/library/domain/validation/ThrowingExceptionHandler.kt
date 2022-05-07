package pl.uz.mt.library.domain.validation

import pl.uz.mt.library.domain.validation.exception.ValidationErrorException

class ThrowingExceptionHandler : ExceptionHandler {

    override fun add(exception: ValidationErrorException) = throw exception
}
