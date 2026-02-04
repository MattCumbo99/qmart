package com.mattrition.qmart.cart

import com.mattrition.qmart.shop.ItemListing
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CartItemService(
    private val cartItemRepo: CartItemRepository
) {
    fun getCartItemsByUserId(userId: UUID) = cartItemRepo.findCartItemsByUserId(userId)

    fun deleteCartItemsByUserId(userId: UUID) = cartItemRepo.deleteCartItemsByUserId(userId)

    fun addItemToCart(userId: UUID, listing: ItemListing) {
        val item = cartItemRepo.findCartItemByUserIdAndListingId(userId, listing.id!!)
    }

    fun updateCartItemQuantity(cartItemId: UUID, newQuantity: Int) {
        val item = cartItemRepo.findById(cartItemId)?.get() ?:
            throw RuntimeException("CartItem ID is not present in DB: $cartItemId")

        if (newQuantity <= 0) {
            cartItemRepo.delete(item)
        } else {
            cartItemRepo.save(item.copy(quantity = newQuantity))
        }
    }
}