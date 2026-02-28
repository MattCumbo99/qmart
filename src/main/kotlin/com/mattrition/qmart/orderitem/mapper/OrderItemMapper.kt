package com.mattrition.qmart.orderitem.mapper

import com.mattrition.qmart.cart.dto.CartItemWithListingDto
import com.mattrition.qmart.orderitem.OrderItem
import com.mattrition.qmart.orderitem.dto.OrderItemDto
import java.util.UUID

object OrderItemMapper {
    fun toDto(orderItem: OrderItem) =
        OrderItemDto(
            orderItemId = orderItem.orderItemId!!,
            orderId = orderItem.orderId!!,
            listingId = orderItem.listingId!!,
            sellerId = orderItem.sellerId!!,
            quantity = orderItem.quantity,
            listingPrice = orderItem.listingPrice,
            status = orderItem.status,
            paidAt = orderItem.paidAt,
            listingTitle = orderItem.listingTitle,
            listingDescription = orderItem.listingDescription,
            listingImageUrl = orderItem.listingImageUrl,
        )

    /** Converts a cart item DTO to an order item DTO. */
    fun fromCartItemDto(cartItemDto: CartItemWithListingDto) =
        OrderItemDto(
            listingId = cartItemDto.itemListing.id!!,
            sellerId = cartItemDto.itemListing.sellerId,
            quantity = cartItemDto.quantity,
            listingPrice = cartItemDto.itemListing.price,
            listingTitle = cartItemDto.itemListing.title,
            listingDescription = cartItemDto.itemListing.description,
            listingImageUrl = cartItemDto.itemListing.imageUrl,
        )

    fun asNewEntity(
        orderItemDto: OrderItemDto,
        orderId: UUID,
    ) = OrderItem(
        orderId = orderId,
        listingId = orderItemDto.listingId,
        sellerId = orderItemDto.sellerId,
        quantity = orderItemDto.quantity,
        listingPrice = orderItemDto.listingPrice,
        paidAt = orderItemDto.paidAt,
        listingTitle = orderItemDto.listingTitle,
        listingDescription = orderItemDto.listingDescription,
        listingImageUrl = orderItemDto.listingImageUrl,
    )
}
