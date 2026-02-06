package com.mattrition.qmart.cart

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CartItemRepository : JpaRepository<CartItem, UUID> {
    fun findCartItemsByUserId(userId: UUID): List<CartItem>

    fun findCartItemByUserIdAndListingId(
        userId: UUID,
        listingId: UUID,
    ): CartItem?

    fun deleteCartItemsByUserId(userId: UUID)
}
