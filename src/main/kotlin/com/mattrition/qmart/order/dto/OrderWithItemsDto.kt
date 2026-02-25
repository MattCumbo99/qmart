package com.mattrition.qmart.order.dto

import com.mattrition.qmart.orderitem.dto.OrderItemDto

data class OrderWithItemsDto(
    val order: OrderDto,
    val orderItems: List<OrderItemDto>,
)
