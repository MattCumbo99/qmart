package com.mattrition.qmart.shop

import com.mattrition.qmart.user.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ItemListingService(
    private val itemListingRepo: ItemListingRepository,
    private val userRepo: UserRepository,
) {
    fun getAllListings(): List<ItemListingDto> =
        itemListingRepo.findAll().map { listing ->
            val sellerUsername = userRepo.findById(listing.sellerId!!).get().username

            listing.toDto(sellerUsername)
        }

    fun getListingById(id: UUID): ItemListingDto? {
        val listing = itemListingRepo.findById(id)?.get() ?: return null
        val sellerUsername = userRepo.findById(listing.sellerId!!).get().username

        return listing.toDto(sellerUsername)
    }

    fun getListingsByUsername(username: String): List<ItemListingDto> {
        val userId = userRepo.findByUsernameIgnoreCase(username)?.id ?: return emptyList()

        return itemListingRepo.findItemListingsBySellerId(userId).map { it.toDto(username) }
    }

    @Transactional fun deleteListingById(id: UUID) = itemListingRepo.deleteItemListingById(id)

    fun createListing(itemListing: ItemListingDto): ItemListingDto {
        val listingEntry =
            ItemListing(
                sellerId = itemListing.sellerId,
                title = itemListing.title,
                description = itemListing.description,
                price = itemListing.price,
                imageUrl = itemListing.imageUrl,
            )

        return itemListingRepo.save(listingEntry).toDto(itemListing.sellerUsername)
    }
}
