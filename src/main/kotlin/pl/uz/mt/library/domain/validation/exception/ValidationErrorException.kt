package pl.uz.mt.library.domain.validation.exception

data class ValidationErrorException(val type: String, override val message: String) : RuntimeException(message)
