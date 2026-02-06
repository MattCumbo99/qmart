package com.mattrition.qmart.cart

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "cart_items")
data class CartItem(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column("cart_item_id")
    val cartItemId: UUID? = null,
    @Column("user_id") val userId: UUID? = null,
    @Column("listing_id") val listingId: UUID? = null,
    val quantity: Int = 1,
)
