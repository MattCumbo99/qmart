package com.mattrition.qmart.orderitem

import com.mattrition.qmart.orderitem.dto.OrderItemDto
import com.mattrition.qmart.user.UserRole
import jakarta.annotation.security.RolesAllowed
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/order-items")
class OrderItemController(
    private val orderItemService: OrderItemService,
) {
    @GetMapping("/orderId/{orderId}")
    @RolesAllowed(UserRole.USER)
    fun getOrderItems(
        @PathVariable orderId: UUID,
    ): List<OrderItemDto> = orderItemService.getOrderItems(orderId)

    @GetMapping("/sellerId/{sellerId}")
    @RolesAllowed(UserRole.USER)
    fun getOrderItemsSoldBy(
        @PathVariable sellerId: UUID,
    ): List<OrderItemDto> = orderItemService.getOrderItemsBySeller(sellerId)
}
