package pl.uz.mt.library.domain.validation

import pl.uz.mt.library.domain.validation.exception.ValidationErrorException

class AggregatingExceptionHandler(val exceptionErrors: ArrayList<ValidationErrorException> = ArrayList()) : ExceptionHandler {

    override fun add(exception: ValidationErrorException) {
        exceptionErrors.add(exception)
    }

    fun hasExceptions(): Boolean = exceptionErrors.isNotEmpty()
}
