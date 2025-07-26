package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Erros
import com.mercadolivro.enums.Role
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Service
class CustomerService(private val customerRepository: CustomerRepository,
                      private val bookService: BookService,
                      private val bcrypt: BCryptPasswordEncoder) {

    fun getAll(name: String?): List<CustomerModel> {

        return name?.let {
            customerRepository.findByNameContaining(it)
        } ?: customerRepository.findAll().toList();
    }

    fun create(customer: CustomerModel) {
        var customerCopy = customer.copy(
            roles = setOf(Role.CUSTOMER),
            password = bcrypt.encode(customer.password)
        )
        customerRepository.save(customerCopy)
    }

    fun getById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow { NotFoundException(Erros.ML201.message.format(id), Erros.ML201.code) }
    }

    fun updateCustomer(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
            customerRepository.save(customer)
    }

    fun deleteCustomer(@PathVariable id: Int) {
        var customer = getById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvaliable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}