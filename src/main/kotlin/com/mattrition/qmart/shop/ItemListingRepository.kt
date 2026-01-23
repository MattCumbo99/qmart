package com.mattrition.qmart.shop

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ItemListingRepository : JpaRepository<ItemListing, Long> {
    fun findItemListingById(id: UUID): ItemListing?
}