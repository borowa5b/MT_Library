package pl.uz.mt.library.application.exception.handler

import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import io.micronaut.core.convert.exceptions.ConversionErrorException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import pl.uz.mt.library.application.exception.ApplicationException
import pl.uz.mt.library.application.exception.ValidationException
import pl.uz.mt.library.application.response.ValidationErrorResponse
import java.util.logging.Logger

private val LOG = Logger.getLogger(ApplicationExceptionHandler::class.java.name)

@Produces
@Singleton
@Requires(classes = [Exception::class, ApplicationExceptionHandler::class])
class ApplicationExceptionHandler : ExceptionHandler<Exception, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: Exception): HttpResponse<ExceptionResponse> {
        when (exception) {
            is ValidationException -> return handleValidationException(exception)
            is ApplicationException -> return handleApplicationException(exception)
        }
        LOG.warning("Server error occurred: ${exception.stackTraceToString()}")
        return HttpResponse.serverError(ExceptionResponse("library:internal-server-error", exception.message));
    }
}

@Primary
@Produces
@Singleton
@Requires(classes = [ConversionErrorException::class, ConversionErrorExceptionHandler::class])
class ConversionErrorExceptionHandler : ExceptionHandler<ConversionErrorException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, exception: ConversionErrorException): HttpResponse<*> {
        when (exception.cause?.cause) {
            is ValidationException -> return handleValidationException(exception.cause!!.cause as ValidationException)
        }

        LOG.info("Conversion error occurred: ${exception.stackTraceToString()}")
        return HttpResponse.badRequest(ExceptionResponse("library:conversion-error", exception.message))
    }
}

private fun handleValidationException(exception: ValidationException): HttpResponse<ExceptionResponse> {
    LOG.info("Validation error occurred: ${exception.stackTraceToString()}")
    return HttpResponse.badRequest(
        ExceptionResponse(
            exception.type,
            exception.message,
            exception.errors.map { ValidationErrorResponse(it.type, it.message) })
    )
}

private fun handleApplicationException(exception: ApplicationException): HttpResponse<ExceptionResponse> {
    LOG.info("Bad request occurred: ${exception.stackTraceToString()}")
    return HttpResponse
        .status<ExceptionResponse>(exception.httpStatus)
        .body(ExceptionResponse(exception.type, exception.message))
}

data class ExceptionResponse(
    val type: String,
    val message: String?,
    val errors: List<ValidationErrorResponse>? = null
)
