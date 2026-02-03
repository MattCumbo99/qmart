package com.mattrition.qmart.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "order_items")
data class OrderItem(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id")
    val orderItemId: UUID? = null,
    @Column(name = "order_id", nullable = false) val orderId: UUID? = null,
    @Column(name = "listing_id", nullable = false) val listingId: UUID? = null,
    @Column(name = "seller_id", nullable = false) val sellerId: UUID? = null,
    val quantity: Int = 0,
    @Column(name = "price_each") val priceEach: BigDecimal = BigDecimal.ZERO,
)
