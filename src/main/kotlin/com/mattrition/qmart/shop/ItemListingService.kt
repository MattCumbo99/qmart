package com.mattrition.qmart.shop

import com.mattrition.qmart.user.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ItemListingService(
    private val repo: ItemListingRepository,
    private val userRepo: UserRepository
) {
    fun getAllListings(): List<ItemListing> = repo.findAll()

    fun getListingById(id: UUID): ItemListing? = repo.findItemListingById(id)

    fun getListingByUsername(username: String): List<ItemListing> {
        val userId = userRepo.findByUsernameIgnoreCase(username)?.id ?: return emptyList()

        return repo.findItemListingsBySellerId(userId)
    }

    fun deleteListingById(id: UUID) = repo.deleteItemListingById(id)

    fun createListing(itemListing: ItemListing): ItemListing = repo.save(itemListing)
}