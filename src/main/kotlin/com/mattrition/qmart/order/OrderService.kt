package com.mattrition.qmart.order

import com.mattrition.qmart.cart.CartItemService
import com.mattrition.qmart.exception.BadRequestException
import com.mattrition.qmart.exception.ForbiddenException
import com.mattrition.qmart.exception.NotFoundException
import com.mattrition.qmart.order.dto.OrderDto
import com.mattrition.qmart.order.dto.OrderWithItemsDto
import com.mattrition.qmart.order.mapper.OrderMapper
import com.mattrition.qmart.orderitem.OrderItemRepository
import com.mattrition.qmart.orderitem.mapper.OrderItemMapper
import com.mattrition.qmart.user.BalanceService
import com.mattrition.qmart.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val userRepository: UserRepository,
    private val cartItemService: CartItemService,
    private val balanceService: BalanceService,
) {
    fun getOrdersBoughtBy(buyerId: UUID): List<OrderDto> = orderRepository.findOrdersByBuyerId(buyerId).map { OrderMapper.toDto(it) }

    fun getOrdersBoughtBy(username: String): List<OrderDto> {
        val user =
            userRepository.findByUsernameIgnoreCase(username)
                ?: throw NotFoundException("User $username not found")

        return getOrdersBoughtBy(user.id!!)
    }

    /**
     * Saves a new order and its items to the database using the buyer's associated cart items. Once
     * an order is made, it clears the cart items being held by the buyer.
     *
     * @throws BadRequestException If the buyer's cart is empty.
     * @throws ForbiddenException If the buyer doesn't have enough money to make the order.
     */
    @Transactional
    fun createOrder(orderInfo: OrderDto): OrderWithItemsDto {
        val cartItems = cartItemService.getCartItemsByUserId(orderInfo.buyerId)

        cartItems.ifEmpty { throw BadRequestException("Order has no items!") }

        balanceService.deductBalance(orderInfo.buyerId, orderInfo.totalPaid)

        val orderEntity = OrderMapper.asNewEntity(orderInfo)
        val newOrder = OrderMapper.toDto(orderRepository.save(orderEntity))

        val items =
            cartItems.map { cartItem ->
                // Save each item to the database
                val cartOrderItem = OrderItemMapper.fromCartItemDto(cartItem)
                val itemEntity = OrderItemMapper.asNewEntity(cartOrderItem, newOrder.orderId!!)
                val newItem = orderItemRepository.save(itemEntity)

                OrderItemMapper.toDto(newItem)
            }

        cartItemService.deleteCartItemsByUserId(orderInfo.buyerId)

        return OrderWithItemsDto(newOrder, items)
    }
}
