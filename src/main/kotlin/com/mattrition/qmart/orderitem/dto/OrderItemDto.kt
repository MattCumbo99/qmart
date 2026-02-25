package com.mattrition.qmart.orderitem.dto

import com.mattrition.qmart.orderitem.OrderItemStatus
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderItemDto(
    val orderItemId: UUID? = null,
    val orderId: UUID? = null,
    val listingId: UUID,
    val sellerId: UUID,
    val quantity: Int,
    val priceEach: BigDecimal,
    val status: OrderItemStatus = OrderItemStatus.PAID_PENDING_SHIPMENT,
    val paidAt: LocalDateTime = LocalDateTime.now(),
)
