package com.mercadolivro.validation

import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class BookAvaliableValidator(val bookService: BookService): ConstraintValidator<BookStatusAvaliable, Set<Int>> {

    override fun isValid(ids: Set<Int>?, constraint: ConstraintValidatorContext?): Boolean {

        return bookService.statusAvailable(ids);
    }

}