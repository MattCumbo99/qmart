package com.mattrition.qmart.shop

import java.math.BigDecimal
import java.util.UUID

data class ItemListingDto(
    val id: UUID,
    val title: String,
    val description: String,
    val price: BigDecimal,
    val imageUrl: String?,
    val sellerId: UUID,
    val sellerUsername: String,
)
