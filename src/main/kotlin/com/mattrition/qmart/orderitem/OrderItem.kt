package com.mattrition.qmart.orderitem

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "order_items")
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id", nullable = false)
    val orderItemId: UUID? = null,
    @Column(name = "order_id", nullable = false) val orderId: UUID? = null,
    @Column(name = "listing_id", nullable = false) val listingId: UUID? = null,
    @Column(name = "seller_id", nullable = false) val sellerId: UUID? = null,
    @Column(nullable = false) val quantity: Int = 0,
    @Column(name = "price_each", nullable = false) val priceEach: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: OrderItemStatus = OrderItemStatus.PAID_PENDING_SHIPMENT,
    @Column(name = "paid_at", nullable = false) val paidAt: LocalDateTime = LocalDateTime.now(),
)
