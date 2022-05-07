package pl.uz.mt.library.rental.domain.model

import org.hibernate.Hibernate
import pl.uz.mt.library.rental.application.dto.RentalDto
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "Rental")
data class RentalEntity(
    @Id val id: String,
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "book_id") val book: RentedBookEntity,
    val rentedFrom: OffsetDateTime,
    val rentedTo: OffsetDateTime,
    @Enumerated(EnumType.STRING)
    val status: RentalStatus
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as RentalEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , rentedFrom = $rentedFrom , rentedTo = $rentedTo , status = $status )"
    }

    fun toDto(): RentalDto = RentalDto(id, book.toDto(), rentedFrom, rentedTo, status)

    companion object {
        fun fromDto(rentalDto: RentalDto): RentalEntity =
            rentalDto.let {
                RentalEntity(
                    it.id,
                    RentedBookEntity.fromDto(it.book),
                    it.rentedFrom,
                    it.rentedTo,
                    it.status
                )
            }
    }
}
