package com.mattrition.qmart.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface OrderRepository : JpaRepository<Order, UUID> {
    fun findOrdersByBuyerId(userId: UUID): List<Order>

    @Query(
        """
            SELECT o FROM Order o
            JOIN o.orderItems oi
            WHERE oi.sellerId = :sellerId
                AND oi.status = 'PAID_PENDING_SHIPMENT'
            ORDER BY oi.paidAt ASC
        """,
    )
    fun findUnfinishedOrdersFromSeller(sellerId: UUID): List<Order>
}
