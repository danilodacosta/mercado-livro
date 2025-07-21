package com.mercadolivro.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [ BookAvaliableValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class BookStatusAvaliable(
    val message: String = "Não é possivel realizar uma venda com status CANCELADO ou DELETADO",
    val groups: Array<KClass<*>> = [],
    val payload : Array<KClass<out Payload>> = []
)
