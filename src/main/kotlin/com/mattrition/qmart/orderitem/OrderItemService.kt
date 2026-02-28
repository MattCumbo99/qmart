package com.mattrition.qmart.orderitem

import com.mattrition.qmart.exception.NotFoundException
import com.mattrition.qmart.order.OrderRepository
import com.mattrition.qmart.order.mapper.OrderMapper
import com.mattrition.qmart.orderitem.dto.OrderItemDto
import com.mattrition.qmart.orderitem.dto.OrderItemWithShippingDto
import com.mattrition.qmart.orderitem.mapper.OrderItemMapper
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class OrderItemService(
    private val orderItemRepository: OrderItemRepository,
    private val orderRepository: OrderRepository,
) {
    /**
     * Gets order items attached to an order entity.
     *
     * @throws NotFoundException If the order doesn't exist.
     */
    fun getOrderItemsFromOrder(id: UUID): List<OrderItemDto> {
        orderRepository.findById(id).orElseThrow {
            throw NotFoundException("Order with ID $id not found.")
        }

        val orderItems = orderItemRepository.findOrderItemsByOrderId(id)

        return orderItems.map { OrderItemMapper.toDto(it) }
    }

    /**
     * Retrieves order items associated with a seller and sorts them by how early the item was paid
     * for and attaches the order's shipping info.
     */
    fun getOrderItemsBySeller(
        sellerId: UUID,
        status: OrderItemStatus?,
    ): List<OrderItemWithShippingDto> =
        orderItemRepository.findSellerItems(sellerId, status).map { orderItem ->
            val orderItemDto = OrderItemMapper.toDto(orderItem)

            val originalOrder = orderRepository.findById(orderItem.orderId!!).get()
            val shippingInfo = OrderMapper.toDto(originalOrder).extractShipping()

            OrderItemWithShippingDto(orderItemDto, shippingInfo)
        }
}
