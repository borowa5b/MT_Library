package pl.uz.mt.library.infrastructure.util

import org.junit.jupiter.api.Test

class DefaultIdGeneratorTest {

    @Test
    fun shouldGeneratePublicId() {
        // given
        val generator = DefaultIdGenerator()

        // when
        val result = generator.generate("BK")

        // then
        assert(isValid(result))
    }

    private fun isValid(identifier: String): Boolean {
        val identifierPrefixNumerically = identifier.substring(0, 2).map { it.code }.joinToString(separator = "")
        val number = "$identifierPrefixNumerically${identifier.substring(2, identifier.length)}"
        var checksum = 0

        for (i in number.length - 1 downTo 0 step 2) {
            checksum += number.get(i) - '0'
        }
        for (i in number.length - 2 downTo 0 step 2) {
            val n: Int = (number.get(i) - '0') * 2
            checksum += if (n > 9) n - 9 else n
        }
        return checksum % 10 == 0
    }
}
