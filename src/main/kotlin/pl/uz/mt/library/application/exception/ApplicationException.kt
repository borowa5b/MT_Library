package pl.uz.mt.library.application.exception

import io.micronaut.http.HttpStatus

open class ApplicationException(val httpStatus: HttpStatus, val type: String, override val message: String) :
    RuntimeException(message)
