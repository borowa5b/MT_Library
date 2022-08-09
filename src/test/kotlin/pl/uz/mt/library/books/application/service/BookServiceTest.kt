package pl.uz.mt.library.books.application.service

import io.micronaut.context.event.ApplicationEventPublisher
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import pl.uz.mt.library.books.helpers.BooksObjects.addBookCommand
import pl.uz.mt.library.books.helpers.BooksObjects.bookDto
import pl.uz.mt.library.books.helpers.repository.InMemoryBookRepository
import pl.uz.mt.library.books.infrastructure.BookIdGenerator
import pl.uz.mt.library.domain.DomainEvent
import pl.uz.mt.library.infrastructure.util.DefaultIdGenerator

@ExtendWith(MockitoExtension::class)
internal class BookServiceTest {

    private val repository = InMemoryBookRepository()

    @Mock
    private lateinit var eventPublisher: ApplicationEventPublisher<DomainEvent>
    private lateinit var service: BookService

    @BeforeEach
    fun beforeEach() {
        repository.clear()
        service = BookService(repository, BookIdGenerator(DefaultIdGenerator()), eventPublisher)
    }

    @Test
    fun shouldAddBook() {
        // given
        val command = addBookCommand()

        // when
        val result = service.addBook(command)

        // then
        assert(result.isNotBlank())
        assert(repository.findByTitle(command.title) != null)
    }

    @Test
    fun shouldRentBook() {
        // given
        val book = repository.save(bookDto())

        // when
        service.rentBook(book.id)

        // then
        val rentedBook = repository.findByTitle(book.title)!!
        assert(rentedBook.availableQuantity == rentedBook.quantity - 1)
    }

    @Test
    fun shouldReturnBook() {
        // given

        // when

        // then
    }

    @Test
    fun shouldRemoveBook() {
        // given

        // when

        // then
    }

    @Test
    fun shouldGetBooks() {
        // given

        // when

        // then
    }
}
