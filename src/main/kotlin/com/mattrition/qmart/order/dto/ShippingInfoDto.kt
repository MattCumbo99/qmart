package com.mattrition.qmart.order.dto

data class ShippingInfoDto(
    val firstName: String,
    val lastName: String,
    val address1: String,
    val address2: String?,
    val city: String,
    val state: String,
    val zip: String,
    val phone: String,
)
