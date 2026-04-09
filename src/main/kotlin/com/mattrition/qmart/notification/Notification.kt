package com.mattrition.qmart.notification

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime
import java.util.UUID

@Entity
@Table(name = "notifications")
class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    var id: UUID? = null,
    @Column(name = "user_id", nullable = false) var userId: UUID,
    @Column(nullable = false) var message: String,
    @Column(nullable = false) var route: String,
    @Column(name = "read_at") var readAt: OffsetDateTime? = null,
    @Column(name = "created_at", nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),
    @Column(name = "deleted_at") var deletedAt: OffsetDateTime? = null,
)
