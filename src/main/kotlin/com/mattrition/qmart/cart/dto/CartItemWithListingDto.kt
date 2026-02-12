package com.mattrition.qmart.cart.dto

import com.mattrition.qmart.shop.dto.ItemListingDto
import java.util.UUID

/**
 * Represents a cart item merged with listing information. Avoids having to make two separate calls
 * in order to retrieve listing information from a cart item listing ID.
 */
data class CartItemWithListingDto(
    val cartItemId: UUID,
    val quantity: Int,
    val itemListing: ItemListingDto,
)
