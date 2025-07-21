package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvaliable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(
    @field:NotEmpty(message = "Nome deve ser informado")
    var name: String,

    @field:Email(message = "E-mail dever ser válido")
    @EmailAvaliable
    var email: String,

    @field:NotEmpty(message = "A senha deve ser informada")
    var password: String
)