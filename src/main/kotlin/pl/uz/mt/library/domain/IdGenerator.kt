package pl.uz.mt.library.domain

interface IdGenerator {

    fun generate(prefix: String): String
}
