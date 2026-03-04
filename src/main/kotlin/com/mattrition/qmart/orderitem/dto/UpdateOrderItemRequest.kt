package com.mattrition.qmart.orderitem.dto

import com.mattrition.qmart.orderitem.OrderItemStatus

data class UpdateOrderItemRequest(
    val status: OrderItemStatus? = null,
)
