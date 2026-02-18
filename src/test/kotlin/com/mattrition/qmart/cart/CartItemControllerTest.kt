package com.mattrition.qmart.cart

import com.mattrition.qmart.BaseH2Test
import com.mattrition.qmart.shop.ItemListing
import com.mattrition.qmart.shop.ItemListingRepository
import com.mattrition.qmart.shop.dto.ItemListingDto
import com.mattrition.qmart.shop.dto.toDto
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod.DELETE
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import kotlin.jvm.optionals.getOrNull

class CartItemControllerTest : BaseH2Test() {
    companion object {
        const val BASE_PATH = "/api/cart-items"
    }

    @Autowired lateinit var itemListingRepository: ItemListingRepository

    @Autowired lateinit var cartItemRepository: CartItemRepository

    private lateinit var listing1: ItemListing
    private lateinit var listing1Dto: ItemListingDto
    private lateinit var listing2: ItemListing
    private lateinit var listing2Dto: ItemListingDto

    @BeforeAll
    fun initializeListings() {
        listing1 =
            itemListingRepository.save(
                ItemListing(
                    sellerId = TestUsers.admin.id,
                    title = "Listing 1",
                    description = "Test listing",
                    price = BigDecimal(10.0),
                ),
            )
        listing1Dto = listing1.toDto(TestUsers.admin.username)

        listing2 =
            itemListingRepository.save(
                ItemListing(
                    sellerId = TestUsers.moderator.id,
                    title = "Listing 2",
                    description = "Test listing",
                    price = BigDecimal(25.0),
                ),
            )
        listing2Dto = listing2.toDto(TestUsers.moderator.username)
    }

    private lateinit var cartItem1: CartItem
    private lateinit var cartItem2: CartItem

    @BeforeEach
    fun addCartItems() {
        val item1 = CartItem(userId = TestUsers.user.id, listingId = listing1.id, quantity = 1)

        val item2 = CartItem(userId = TestUsers.user.id, listingId = listing2.id, quantity = 1)

        cartItem1 = cartItemRepository.save(item1)
        cartItem2 = cartItemRepository.save(item2)
    }

    @Nested
    inner class AddItem {
        @Test
        fun `adding item to other cart returns forbidden 403`() {
            mockRequest(
                requestType = POST,
                path = "$BASE_PATH/user/${TestUsers.user.id}",
                token = TestTokens.admin,
                body = listing1Dto,
            ).andExpect(status().isForbidden)
        }

        @Test
        fun `should add item to cart`() {
            cartItemRepository.findCartItemsByUserId(TestUsers.superadmin.id!!).size shouldBe 0

            mockRequest(
                requestType = POST,
                path = "$BASE_PATH/user/${TestUsers.superadmin.id}",
                token = TestTokens.superadmin,
                body = listing1Dto,
            ).andExpect(status().isOk)

            val userItems = cartItemRepository.findCartItemsByUserId(TestUsers.superadmin.id!!)
            userItems.size shouldBe 1
            userItems.first().listingId shouldBe listing1.id
        }
    }

    @Nested
    inner class RemoveItems {
        @Test
        fun `clearing items from another cart should return forbidden 403`() {
            mockRequest(
                requestType = DELETE,
                path = "$BASE_PATH/user/${TestUsers.user.id}",
                token = TestTokens.superadmin,
            ).andExpect(status().isForbidden)
        }

        @Test
        fun `should clear all cart items`() {
            cartItemRepository.findCartItemsByUserId(TestUsers.user.id!!).size shouldBeGreaterThan 0

            mockRequest(
                requestType = DELETE,
                path = "$BASE_PATH/user/${TestUsers.user.id}",
                token = TestTokens.user,
            ).andExpect(status().isOk)

            cartItemRepository.findCartItemsByUserId(TestUsers.user.id!!).size shouldBe 0
        }

        @Test
        fun `removing an item from another cart should return forbidden 403`() {
            mockRequest(
                requestType = DELETE,
                path = "$BASE_PATH/user/${TestUsers.user.id}/listing/${listing1.id}",
                token = TestTokens.superadmin,
            ).andExpect(status().isForbidden)
        }

        @Test
        fun `should remove an item from cart`() {
            cartItemRepository.findById(cartItem1.cartItemId!!).getOrNull().shouldNotBeNull()

            mockRequest(
                requestType = DELETE,
                path = "$BASE_PATH/user/${TestUsers.user.id}/listing/${listing1.id}",
                token = TestTokens.user,
            ).andExpect(status().isOk)

            cartItemRepository.findById(cartItem1.cartItemId!!).getOrNull().shouldBeNull()
            cartItemRepository.findCartItemsByUserId(TestUsers.user.id!!).size shouldBe 1
        }
    }

    @Nested
    inner class GetCartItems {
        @Test
        fun `getting items from another cart should return forbidden 403`() {
            mockRequest(
                requestType = GET,
                path = "$BASE_PATH/user/${TestUsers.user.id}",
                token = TestTokens.superadmin,
            ).andExpect(status().isForbidden)
        }

        @Test
        fun `should get items from a users cart`() {
            mockRequest(
                requestType = GET,
                path = "$BASE_PATH/user/${TestUsers.user.id}",
                token = TestTokens.user,
            ).andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(2))
        }
    }
}
