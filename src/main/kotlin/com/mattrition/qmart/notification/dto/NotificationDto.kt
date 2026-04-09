package com.mattrition.qmart.notification.dto

import java.time.OffsetDateTime
import java.util.UUID

data class NotificationDto(
    val id: UUID? = null,
    val message: String,
    val route: String,
    val readAt: OffsetDateTime? = null,
    val createdAt: OffsetDateTime = OffsetDateTime.now(),
)
