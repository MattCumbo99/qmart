package com.mattrition.qmart.order

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderRepository : JpaRepository<Order, UUID> {
    fun findOrdersByBuyerId(userId: UUID): List<Order>
}
