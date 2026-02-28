package com.mattrition.qmart.orderitem

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface OrderItemRepository : JpaRepository<OrderItem, UUID> {
    fun findOrderItemsByOrderId(orderId: UUID): List<OrderItem>

    @Query(
        """
        SELECT oi FROM OrderItem oi
        WHERE oi.sellerId = :sellerId
            AND (:status IS NULL OR oi.status = :status)
        ORDER BY oi.paidAt ASC
    """,
    )
    fun findSellerItems(
        sellerId: UUID,
        status: OrderItemStatus?,
    ): List<OrderItem>
}
