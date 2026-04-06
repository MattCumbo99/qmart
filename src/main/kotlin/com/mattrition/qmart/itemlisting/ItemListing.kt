package com.mattrition.qmart.itemlisting

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "item_listings")
class ItemListing(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: UUID? = null,
    @Column(name = "seller_id", nullable = false) var sellerId: UUID? = null,
    var title: String = "",
    var description: String = "",
    var price: BigDecimal = BigDecimal.ZERO,
    @Column(name = "image_url") var imageUrl: String? = null,
    @Column(name = "created_at") var createdAt: OffsetDateTime = OffsetDateTime.now(),
    @Column(name = "updated_at") var updatedAt: OffsetDateTime = OffsetDateTime.now(),
)
