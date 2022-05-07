package pl.uz.mt.library

import io.micronaut.runtime.Micronaut.build
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "library-service",
        version = "1.0",
        description = "Library like application created for master thesis purposes"
    )
)
class Application

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("pl.uz.mt.library")
        .start()
}
