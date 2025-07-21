package com.mercadolivro.controller

import com.mercadolivro.controller.mapper.PurchaseMapper
import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("purchases")
class PurchaseController(
    val purchaseService: PurchaseService,
    val purchaseMapper: PurchaseMapper) {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostPurchaseRequest) {
            purchaseService.create(purchaseMapper.toModel(request))
    //var customer = customerService.getById(book.customerId)
        //bookService.create(book.toModel(customer))
    }
/*
    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable: Pageable,
               @RequestParam name: String?): Page<BookResponse> {
        return bookService.getAll(pageable, name).map { it.toResponse() }
    }


    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Int): BookResponse {
        return bookService.getById(id).toResponse()
    }

    @GetMapping("/actives")
    fun getBookActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findActives(pageable).map { it.toResponse() }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBook(@PathVariable id: Int, @RequestBody putBookRequest: PutBookRequest) {
        var bookSaved = bookService.getById(id);
        bookService.updateBook(putBookRequest.toModel(bookSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Int) {
        bookService.deleteBook(id)
    }*/
}