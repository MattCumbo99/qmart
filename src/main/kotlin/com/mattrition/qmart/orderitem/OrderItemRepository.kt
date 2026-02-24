package com.mattrition.qmart.orderitem

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderItemRepository : JpaRepository<OrderItem, UUID> {
    fun findOrderItemsByOrderId(orderId: UUID): List<OrderItem>

    fun findOrderItemsBySellerId(sellerId: UUID): List<OrderItem>
}
