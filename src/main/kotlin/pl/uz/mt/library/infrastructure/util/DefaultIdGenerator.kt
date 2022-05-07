package pl.uz.mt.library.infrastructure.util

import jakarta.inject.Singleton
import pl.uz.mt.library.domain.IdGenerator
import java.security.SecureRandom

@Singleton
class DefaultIdGenerator : IdGenerator {

    companion object {
        private const val LENGTH = 16
    }

    override fun generate(prefix: String): String {
        val randomNumericString = randomNumericString()
        val checkDigit = generateLuhnCheckDigit(prefix, randomNumericString)
        return "$prefix$randomNumericString$checkDigit"
    }

    private fun randomNumericString(): String {
        val secureRandom = SecureRandom()
        return buildString {
            for (index in 0..LENGTH) {
                append(secureRandom.nextInt(10))
            }
        }
    }

    private fun generateLuhnCheckDigit(prefix: String, randomNumericString: String): String {
        val identifier = "${prefix.map { it.code }.joinToString(separator = "")}$randomNumericString"
        var sum = 0

        for (i in identifier.length - 1 downTo 0 step 2) {
            val multiplied = identifier[i].digitToInt() * 2
            sum += if (multiplied > 9) multiplied - 9 else multiplied
        }

        for (i in identifier.length - 2 downTo 0 step 2) {
            sum += identifier[i].digitToInt()
        }
        return "${((10 - sum % 10) % 10)}"
    }
}
