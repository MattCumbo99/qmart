package com.mattrition.qmart.notification

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface NotificationRepository : JpaRepository<Notification, UUID> {
    @Query(
        """
        SELECT n FROM Notification n
        WHERE n.userId = :userId
            AND n.deletedAt IS NULL
        ORDER BY n.createdAt DESC
    """,
    )
    fun findByUser(userId: UUID): List<Notification>
}
