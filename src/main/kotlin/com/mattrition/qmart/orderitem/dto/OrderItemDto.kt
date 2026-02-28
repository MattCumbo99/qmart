package com.mattrition.qmart.orderitem.dto

import com.mattrition.qmart.orderitem.OrderItemStatus
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class OrderItemDto(
    val orderItemId: UUID? = null,
    val orderId: UUID? = null,
    val listingId: UUID,
    val sellerId: UUID,
    val quantity: Int,
    val listingPrice: BigDecimal,
    val status: OrderItemStatus = OrderItemStatus.PAID_PENDING_SHIPMENT,
    val paidAt: OffsetDateTime = OffsetDateTime.now(),
    val listingTitle: String,
    val listingDescription: String? = null,
    val listingImageUrl: String? = null,
)
