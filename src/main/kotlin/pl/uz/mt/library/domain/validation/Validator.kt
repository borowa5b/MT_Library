package pl.uz.mt.library.domain.validation

import pl.uz.mt.library.domain.validation.exception.ValidationErrorException
import pl.uz.mt.library.domain.validation.exception.ValidationExceptionErrorType

object Validator {

    fun isNotNull(field: Any?, fieldName: String, exceptionHandler: ExceptionHandler) {
        if (field == null) {
            exceptionHandler.add(
                ValidationErrorException(
                    ValidationExceptionErrorType.NOT_NULL.type,
                    ValidationExceptionErrorType.NOT_NULL.message.replace("{0}", fieldName)
                )
            )
        }
    }

    fun isNotNullOrBlank(field: String?, fieldName: String, exceptionHandler: ExceptionHandler) {
        if (field == null || field.isBlank()) {
            exceptionHandler.add(
                ValidationErrorException(
                    ValidationExceptionErrorType.NOT_NULL_OR_BLANK.type,
                    ValidationExceptionErrorType.NOT_NULL_OR_BLANK.message.replace("{0}", fieldName)
                )
            )
        }
    }

    fun isGreaterThan(
        field: Int,
        fieldName: String,
        greaterThan: Int,
        exceptionHandler: ExceptionHandler
    ) {
        if (field <= greaterThan) {
            exceptionHandler.add(
                ValidationErrorException(
                    ValidationExceptionErrorType.GREATER_THAN.type,
                    ValidationExceptionErrorType.GREATER_THAN.message
                        .replace("{0}", fieldName)
                        .replace("{1}", greaterThan.toString())
                )
            )
        }
    }

    fun isGreaterOrEqualThan(
        field: Int,
        fieldName: String,
        greaterOrEqualThan: Int,
        exceptionHandler: ExceptionHandler
    ) {
        if (field < greaterOrEqualThan) {
            exceptionHandler.add(
                ValidationErrorException(
                    ValidationExceptionErrorType.GREATER_OR_EQUAL_THAN.type,
                    ValidationExceptionErrorType.GREATER_OR_EQUAL_THAN.message
                        .replace("{0}", fieldName)
                        .replace("{1}", greaterOrEqualThan.toString())
                )
            )
        }
    }
}
