package com.mercadolivro.events.listener

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class UpdateSoldBookListener(val bookService: BookService) {

    @Async
    @EventListener
    fun listener(purchaseEvent: PurchaseEvent) {
        val books = purchaseEvent.purchaseModel.books
        bookService.purchase(books)
    }

}