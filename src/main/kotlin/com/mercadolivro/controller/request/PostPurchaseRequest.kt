package com.mercadolivro.controller.request

import com.mercadolivro.validation.BookStatusAvaliable
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive


data class PostPurchaseRequest(

    @field:NotNull
    @field:Positive
    var customerId: Int,

    @field:NotNull
    @field:BookStatusAvaliable
    var booksId: Set<Int>

)