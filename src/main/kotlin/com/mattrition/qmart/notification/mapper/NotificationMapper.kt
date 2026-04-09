package com.mattrition.qmart.notification.mapper

import com.mattrition.qmart.notification.Notification
import com.mattrition.qmart.notification.dto.NotificationDto
import com.mattrition.qmart.util.EntityMapper

object NotificationMapper : EntityMapper<Notification, NotificationDto> {
    override fun toDto(entity: Notification) =
        NotificationDto(
            id = entity.id,
            message = entity.message,
            route = entity.route,
            readAt = entity.readAt,
            createdAt = entity.createdAt,
        )

    @Deprecated(message = "Unsupported method.", level = DeprecationLevel.ERROR)
    override fun asNewEntity(dto: NotificationDto): Notification = throw UnsupportedOperationException("Method not supported.")
}
