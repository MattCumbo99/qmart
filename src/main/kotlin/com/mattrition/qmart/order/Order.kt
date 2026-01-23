package com.mattrition.qmart.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    val orderId: UUID? = null,

    @Column(name = "buyer_id", nullable = false)
    val buyerId: UUID? = null,

    val status: String = OrderStatus.PENDING,

    @Column(name = "total_paid")
    val totalPaid: BigDecimal = BigDecimal.ZERO,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
)
