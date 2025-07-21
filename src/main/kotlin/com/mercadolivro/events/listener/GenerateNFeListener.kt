package com.mercadolivro.events.listener

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GenerateNFeListener(val purchaseService: PurchaseService) {

    @Async
    @EventListener
    fun listener(purchaseEvent: PurchaseEvent) {
        val nfe = UUID.randomUUID().toString()
        var purchaseModel = purchaseEvent.purchaseModel.copy(nfe = nfe)
        purchaseService.update(purchaseModel)
    }

}