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
import java.time.OffsetDateTime
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
    @Column(name = "listing_price", nullable = false)
    val listingPrice: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: OrderItemStatus = OrderItemStatus.PAID_PENDING_SHIPMENT,
    @Column(name = "paid_at", nullable = false) val paidAt: OffsetDateTime = OffsetDateTime.now(),
    @Column(name = "listing_title", nullable = false) val listingTitle: String = "",
    @Column(name = "listing_description") val listingDescription: String? = null,
    @Column(name = "listing_image_url") val listingImageUrl: String? = null,
)
