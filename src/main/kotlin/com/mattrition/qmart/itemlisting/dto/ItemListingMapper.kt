package com.mattrition.qmart.itemlisting.dto

import com.mattrition.qmart.itemlisting.ItemListing

/** Converts this item listing into a data transfer object. */
fun ItemListing.toDto(sellerUsername: String) =
    ItemListingDto(
        id = this.id!!,
        title = this.title,
        description = this.description,
        price = this.price,
        imageUrl = this.imageUrl,
        sellerId = this.sellerId!!,
        sellerUsername = sellerUsername,
    )
