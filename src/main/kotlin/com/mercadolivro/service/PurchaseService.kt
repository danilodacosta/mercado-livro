package com.mercadolivro.service

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher) {

    fun create(purchase: PurchaseModel) {
        this.save(purchase)
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))
    }

    fun update(purchase: PurchaseModel) {
       this.save(purchase)
    }
    /*
    fun getAll(pageable: Pageable, name: String?): Page<BookModel> {
        return name?.let {
            bookRepository.findByNameContaining(pageable, it)
        } ?: bookRepository.findAll(pageable);
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(pageable, BookStatus.ATIVO)
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
    }*/

    private fun save(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }
}