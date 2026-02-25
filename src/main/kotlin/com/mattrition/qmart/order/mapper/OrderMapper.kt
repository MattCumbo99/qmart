package com.mattrition.qmart.order.mapper

import com.mattrition.qmart.order.Order
import com.mattrition.qmart.order.dto.OrderDto

object OrderMapper {
    /** Converts an [Order] database entity into a data transfer object. */
    fun toDto(order: Order) =
        OrderDto(
            orderId = order.orderId!!,
            buyerId = order.buyerId!!,
            status = order.status,
            totalPaid = order.totalPaid,
            createdAt = order.createdAt,
            shippingFirstname = order.shippingFirstname,
            shippingLastname = order.shippingLastname,
            shippingAddress1 = order.shippingAddress1,
            shippingAddress2 = order.shippingAddress2,
            shippingCity = order.shippingCity,
            shippingState = order.shippingState,
            shippingZip = order.shippingZip,
            shippingPhone = order.shippingPhone,
        )

    /** Converts an order DTO into a savable entity for the Order database. */
    fun asNewEntity(orderDto: OrderDto) =
        Order(
            buyerId = orderDto.buyerId,
            totalPaid = orderDto.totalPaid,
            shippingFirstname = orderDto.shippingFirstname,
            shippingLastname = orderDto.shippingLastname,
            shippingAddress1 = orderDto.shippingAddress1,
            shippingAddress2 = orderDto.shippingAddress2,
            shippingCity = orderDto.shippingCity,
            shippingState = orderDto.shippingState,
            shippingZip = orderDto.shippingZip,
            shippingPhone = orderDto.shippingPhone,
        )
}
