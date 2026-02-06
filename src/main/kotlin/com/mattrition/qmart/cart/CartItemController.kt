package com.mattrition.qmart.cart

import com.mattrition.qmart.shop.ItemListingDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/cart-items")
class CartItemController(
    private val cartService: CartItemService,
) {
    @GetMapping("/userid={userId}")
    fun getCartItemsByUserId(
        @PathVariable userId: UUID,
    ): List<CartItemWithListingDto> = cartService.getCartItemsByUserId(userId)

    @PostMapping("/userid={userId}")
    fun addItemToCart(
        @PathVariable userId: UUID,
        @RequestBody listing: ItemListingDto,
    ): CartItemWithListingDto = cartService.addItemToCart(userId, listing, itemQuantity = 1)

    @DeleteMapping("/userid={userId}")
    fun clearCartItems(
        @PathVariable userId: UUID,
    ) = cartService.deleteCartItemsByUserId(userId)
}
