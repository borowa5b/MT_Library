package pl.uz.mt.library.books.application.service

import io.micronaut.context.event.ApplicationEventPublisher
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import jakarta.inject.Singleton
import pl.uz.mt.library.application.exception.BookNotFoundException
import pl.uz.mt.library.application.filter.PageFilter
import pl.uz.mt.library.books.application.dto.BookDto
import pl.uz.mt.library.books.application.exception.BookAlreadyExistsException
import pl.uz.mt.library.books.application.exception.BookOutOfStockException
import pl.uz.mt.library.books.application.request.AddBookRequest
import pl.uz.mt.library.books.domain.event.BookRemovedEvent
import pl.uz.mt.library.books.domain.repository.BookRepository
import pl.uz.mt.library.books.infrastructure.BookIdGenerator
import pl.uz.mt.library.domain.DomainEvent
import javax.transaction.Transactional

@Singleton
open class BookService(
    private val bookRepository: BookRepository,
    private val bookIdGenerator: BookIdGenerator,
    private val applicationEventPublisher: ApplicationEventPublisher<DomainEvent>
) {

    @Transactional
    open fun addBook(addBookRequest: AddBookRequest): String {
        val bookTitle = addBookRequest.title!!
        if (bookRepository.findByTitle(bookTitle) != null) throw BookAlreadyExistsException(bookTitle)

        val bookDto = BookDto(
            bookIdGenerator.generate(),
            bookTitle,
            addBookRequest.description!!,
            addBookRequest.author!!,
            addBookRequest.year!!,
            addBookRequest.publisher!!,
            addBookRequest.content!!,
            addBookRequest.pages!!,
            addBookRequest.quantity!!,
            addBookRequest.quantity
        )
        return bookRepository.save(bookDto).id
    }

    @Transactional
    open fun rentBook(id: String) {
        val book = bookRepository.findById(id) ?: throw BookNotFoundException(id)
        if (book.availableQuantity == 0) throw BookOutOfStockException(book.title)
        book.availableQuantity = book.availableQuantity - 1
        bookRepository.update(book)
    }

    @Transactional
    open fun returnBook(id: String) {
        val book = bookRepository.findById(id)
        book?.let {
            it.availableQuantity = it.availableQuantity + 1
            bookRepository.update(it)
        }
    }

    @Transactional
    open fun removeBook(id: String) {
        if (bookRepository.findById(id) == null) throw BookNotFoundException(id)
        applicationEventPublisher.publishEvent(BookRemovedEvent(id))
        bookRepository.remove(id)
    }

    open fun getBooks(filter: PageFilter): Page<BookDto> {
        val pageable = Pageable.from(filter.pageNumber, filter.pageSize, filter.sort)
        return bookRepository.findAll(pageable)
    }
}
