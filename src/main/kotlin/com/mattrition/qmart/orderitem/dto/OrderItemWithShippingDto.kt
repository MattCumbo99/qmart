package com.mattrition.qmart.orderitem.dto

import com.mattrition.qmart.order.dto.ShippingInfoDto

data class OrderItemWithShippingDto(
    val orderItem: OrderItemDto,
    val shippingInfo: ShippingInfoDto,
)
