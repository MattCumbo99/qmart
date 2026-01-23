package com.mattrition.qmart.shop

import java.util.UUID

class ItemListingService(
    private val repo: ItemListingRepository
) {
    fun getAllListings(): List<ItemListing> = repo.findAll()

    fun getListingById(id: UUID): ItemListing? = repo.findItemListingById(id)

    fun createListing(itemListing: ItemListing) {

    }
}