package pl.uz.mt.library.rental.infrastructure

import jakarta.inject.Singleton
import pl.uz.mt.library.domain.IdGenerator

@Singleton
class RentalIdGenerator(private val idGenerator: IdGenerator) {

    companion object {
        private const val RENTAL_PREFIX = "RL"
    }

    fun generate(): String = idGenerator.generate(RENTAL_PREFIX)
}
