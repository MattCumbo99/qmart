package com.mattrition.qmart.order.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OrderDto(
    val orderId: UUID? = null,
    val buyerId: UUID,
    val status: String,
    val totalPaid: BigDecimal,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val shippingFirstname: String,
    val shippingLastname: String,
    val shippingAddress1: String,
    val shippingAddress2: String? = null,
    val shippingCity: String,
    val shippingState: String,
    val shippingZip: String,
    val shippingPhone: String,
)
