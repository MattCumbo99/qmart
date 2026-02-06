package com.mattrition.qmart.shop

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/item-listings")
class ItemListingController(
    private val service: ItemListingService,
) {
    @GetMapping fun getItemListings(): List<ItemListingDto> = service.getAllListings()

    @GetMapping("/seller={sellerUsername}")
    fun getItemListingsByUsername(
        @PathVariable sellerUsername: String,
    ): List<ItemListingDto> = service.getListingsByUsername(sellerUsername)

    @GetMapping("/{listingId}")
    fun getItemListing(
        @PathVariable listingId: UUID,
    ): ItemListingDto? = service.getListingById(listingId)

    @PostMapping
    fun createListing(
        @RequestBody itemListing: ItemListingDto,
    ): ItemListingDto = service.createListing(itemListing)

    @DeleteMapping("/{listingId}")
    fun deleteListingById(
        @PathVariable listingId: UUID,
    ) = service.deleteListingById(listingId)
}
