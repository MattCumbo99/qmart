package com.mattrition.qmart.orderitem

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.OffsetDateTime
import java.util.UUID

interface OrderItemRepository : JpaRepository<OrderItem, UUID> {
    fun findOrderItemsByOrderId(orderId: UUID): List<OrderItem>

    fun findByOrderIdAndSellerId(
        orderId: UUID,
        sellerId: UUID,
    ): List<OrderItem>

    @Query(
        """
            SELECT oi FROM OrderItem oi
            WHERE oi.status = 'SHIPPED'
                AND oi.shippedOn < :cutoff
        """,
    )
    fun findAllToComplete(cutoff: OffsetDateTime): List<OrderItem>
}
