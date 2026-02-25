package com.mattrition.qmart.orderitem

import com.mattrition.qmart.orderitem.dto.OrderItemDto
import com.mattrition.qmart.orderitem.mapper.OrderItemMapper
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderItemService(
    private val orderItemRepository: OrderItemRepository,
) {
    fun getOrderItems(orderId: UUID): List<OrderItemDto> =
        orderItemRepository.findOrderItemsByOrderId(orderId).map { OrderItemMapper.toDto(it) }

    fun getOrderItemsBySeller(sellerId: UUID): List<OrderItemDto> =
        orderItemRepository.findOrderItemsBySellerId(sellerId).map { OrderItemMapper.toDto(it) }
}
