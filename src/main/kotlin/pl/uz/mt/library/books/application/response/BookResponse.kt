package pl.uz.mt.library.books.application.response

class BookResponse(
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val publisher: String,
    val year: String,
    val pages: Int,
    val availableQuantity: Int
)
