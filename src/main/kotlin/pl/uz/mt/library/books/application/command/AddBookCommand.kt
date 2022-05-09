package pl.uz.mt.library.books.application.command

data class AddBookCommand(
    val title: String,
    val description: String,
    val author: String,
    val year: String,
    val publisher: String,
    val content: String,
    val pages: Int,
    val quantity: Int
)
