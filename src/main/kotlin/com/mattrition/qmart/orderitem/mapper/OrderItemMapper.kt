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
            priceEach = orderItem.priceEach,
            status = orderItem.status,
        )

    /** Converts a cart item DTO to an order item DTO. */
    fun fromCartItemDto(cartItemDto: CartItemWithListingDto) =
        OrderItemDto(
            listingId = cartItemDto.itemListing.id!!,
            sellerId = cartItemDto.itemListing.sellerId,
            quantity = cartItemDto.quantity,
            priceEach = cartItemDto.itemListing.price,
        )

    fun asNewEntity(
        orderItemDto: OrderItemDto,
        orderId: UUID,
    ) = OrderItem(
        orderId = orderId,
        listingId = orderItemDto.listingId,
        sellerId = orderItemDto.sellerId,
        quantity = orderItemDto.quantity,
        priceEach = orderItemDto.priceEach,
        paidAt = orderItemDto.paidAt,
    )
}
