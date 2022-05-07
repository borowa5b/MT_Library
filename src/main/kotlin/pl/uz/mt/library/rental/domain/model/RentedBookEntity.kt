package pl.uz.mt.library.rental.domain.model

import org.hibernate.Hibernate
import pl.uz.mt.library.rental.application.dto.RentedBookDto
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Book")
data class RentedBookEntity(
    @Id
    val id: String,
    val title: String,
    val content: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as RentedBookEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , title = $title , content = $content )"
    }

    fun toDto(): RentedBookDto = RentedBookDto(id, title, content)

    companion object {
        fun fromDto(rentedBookDto: RentedBookDto): RentedBookEntity =
            rentedBookDto.let { RentedBookEntity(it.id, it.title, it.content) }
    }
}
