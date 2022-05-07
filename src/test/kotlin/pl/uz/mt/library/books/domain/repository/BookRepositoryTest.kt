package pl.uz.mt.library.books.domain.repository

import io.micronaut.data.model.Pageable
import org.junit.jupiter.api.Test
import pl.uz.mt.library.books.helpers.BooksObjects.bookDto
import pl.uz.mt.library.books.helpers.repository.InMemoryBookRepository

class BookRepositoryTest {

    private val repository: BookRepository = InMemoryBookRepository()

    @Test
    fun shouldSaveBook() {
        // given
        val bookToSave = bookDto()

        // when
        val result = repository.save(bookToSave)

        // then
        assert(result.id == bookToSave.id)
        assert(result.title == bookToSave.title)
        assert(result.description == bookToSave.description)
        assert(result.author == bookToSave.author)
        assert(result.year == bookToSave.year)
        assert(result.publisher == bookToSave.publisher)
        assert(result.content == bookToSave.content)
        assert(result.pages == bookToSave.pages)
        assert(result.quantity == bookToSave.quantity)
        assert(result.availableQuantity == bookToSave.quantity)
        assert(result.creationDate == bookToSave.creationDate)
    }

    @Test
    fun shouldUpdateBook() {
        // given
        val bookToUpdate = repository.save(bookDto(availableQuantity = 5)).toDto()
        val newAvailableQuantity = 0
        bookToUpdate.availableQuantity = newAvailableQuantity


        // when
        val result = repository.update(bookToUpdate)


        // then
        assert(result.id == bookToUpdate.id)
        assert(result.title == bookToUpdate.title)
        assert(result.description == bookToUpdate.description)
        assert(result.author == bookToUpdate.author)
        assert(result.year == bookToUpdate.year)
        assert(result.publisher == bookToUpdate.publisher)
        assert(result.content == bookToUpdate.content)
        assert(result.pages == bookToUpdate.pages)
        assert(result.quantity == bookToUpdate.quantity)
        assert(result.availableQuantity == newAvailableQuantity)
        assert(result.creationDate == bookToUpdate.creationDate)
    }

    @Test
    fun shouldFindBookById() {
        // given
        val bookDto = bookDto()
        repository.save(bookDto)

        // when
        val result = repository.findById(bookDto.id)

        // then
        result?.let {
            assert(it.id == bookDto.id)
            assert(it.title == bookDto.title)
            assert(it.description == bookDto.description)
            assert(it.author == bookDto.author)
            assert(it.year == bookDto.year)
            assert(it.publisher == bookDto.publisher)
            assert(it.content == bookDto.content)
            assert(it.pages == bookDto.pages)
            assert(it.quantity == bookDto.quantity)
            assert(it.availableQuantity == bookDto.quantity)
            assert(it.creationDate == bookDto.creationDate)
        }
    }

    @Test
    fun shouldFindBookByTitle() {
        // given
        val bookDto = bookDto()
        repository.save(bookDto)

        // when
        val result = repository.findByTitle(bookDto.title)

        // then
        result?.let {
            assert(it.id == bookDto.id)
            assert(it.title == bookDto.title)
            assert(it.description == bookDto.description)
            assert(it.author == bookDto.author)
            assert(it.year == bookDto.year)
            assert(it.publisher == bookDto.publisher)
            assert(it.content == bookDto.content)
            assert(it.pages == bookDto.pages)
            assert(it.quantity == bookDto.quantity)
            assert(it.availableQuantity == bookDto.quantity)
            assert(it.creationDate == bookDto.creationDate)
        }
    }

    @Test
    fun shouldRemoveBook() {
        // given
        val bookDto = bookDto()
        repository.save(bookDto)

        // when
        repository.remove(bookDto.id)

        // then
        assert(repository.findById(bookDto.id) == null)
    }

    @Test
    fun shouldFindAllBooks() {
        // given
        val bookDto1 = bookDto()
        val bookDto2 = bookDto()
        repository.save(bookDto1)
        repository.save(bookDto2)
        val pageable = Pageable.UNPAGED

        // when
        val result = repository.findAll(pageable)

        // then
        assert(result.content.containsAll(listOf(bookDto1, bookDto2)))
    }
}
