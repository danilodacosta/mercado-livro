package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toModel
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("books")
class BookController(val bookService: BookService, val customerService: CustomerService) {

    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10) pageable: Pageable,
               @RequestParam name: String?): Page<BookResponse> {
        return bookService.getAll(pageable, name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid book: PostBookRequest) {
        var customer = customerService.getById(book.customerId)
        bookService.create(book.toModel(customer))
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
    }
}