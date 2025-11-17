package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Erros
import com.mercadolivro.enums.Role
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.helper.buildCustomer
import com.mercadolivro.helper.buildPurchase
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.repository.PurchaseRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.math.BigDecimal
import java.util.*
import kotlin.test.currentStackTrace

@ExtendWith(MockKExtension::class)
class PurchaseServiceTest {

    @MockK
    private lateinit var purchaseRepository: PurchaseRepository

    @MockK
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    @InjectMockKs
    private lateinit var purchaseService: PurchaseService

    val purchaseEventSlot = slot<PurchaseEvent>()

    @Test
    fun `should create purchase and publish event`() {

        val purchase = buildPurchase()

        every {purchaseRepository.save(purchase)} returns purchase

        every {applicationEventPublisher.publishEvent(any())} just runs

        purchaseService.create(purchase)

        verify(exactly = 1) {purchaseRepository.save(purchase)}
        verify(exactly = 1) {applicationEventPublisher.publishEvent(capture(purchaseEventSlot))}

        assertEquals(purchase, purchaseEventSlot.captured.purchaseModel)

    }

    @Test
    fun `should update purchase`() {

        val purchase = buildPurchase()

        every {purchaseRepository.save(purchase)} returns purchase

        purchaseService.update(purchase)

        verify(exactly = 1) {purchaseRepository.save(purchase)}

    }

}