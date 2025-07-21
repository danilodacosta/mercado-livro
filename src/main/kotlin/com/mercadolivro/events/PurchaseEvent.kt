package com.mercadolivro.events

import com.mercadolivro.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    sorce: Any,
    val purchaseModel: PurchaseModel
): ApplicationEvent(sorce)