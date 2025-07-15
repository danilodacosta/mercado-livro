package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Erros
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class BookService(val bookRepository: BookRepository) {

    fun getAll(pageable: Pageable, name: String?): Page<BookModel> {
        return name?.let {
            bookRepository.findByNameContaining(pageable, it)
        } ?: bookRepository.findAll(pageable);
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(pageable, BookStatus.ATIVO)
    }

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun getById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow {
            NotFoundException(Erros.ML101.message.format(id),Erros.ML101.code)}
    }

    fun updateBook(book: BookModel) {
       bookRepository.save(book)
    }

    fun deleteBook(@PathVariable id: Int) {

        var book = this.getById(id)
        book.status = BookStatus.CANCELADO;

        updateBook(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}