package pl.uz.mt.library.books.domain.model

import org.hibernate.Hibernate
import pl.uz.mt.library.books.application.dto.BookDto
import java.time.OffsetDateTime
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Book")
data class BookEntity(
    @Id
    val id: String,
    val title: String,
    val description: String,
    val author: String,
    val year: String,
    val publisher: String,
    val content: String,
    val pages: Int,
    val quantity: Int,
    val availableQuantity: Int,
    val creationDate: OffsetDateTime = OffsetDateTime.now()
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BookEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , title = $title , description = $description , author = $author , year = $year , publisher = $publisher , content = $content , pages = $pages , quantity = $quantity , availableQuantity = $availableQuantity )"
    }

    fun toDto(): BookDto =
        BookDto(id, title, description, author, year, publisher, content, pages, quantity, availableQuantity, creationDate)

    companion object {
        fun fromDto(bookDto: BookDto) = bookDto.let {
            BookEntity(
                it.id,
                it.title,
                it.description,
                it.author,
                it.year,
                it.publisher,
                it.content,
                it.pages,
                it.quantity,
                it.availableQuantity,
                it.creationDate
            )
        }
    }
}

