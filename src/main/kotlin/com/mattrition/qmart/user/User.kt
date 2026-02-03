package com.mattrition.qmart.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

/**
 * Represents a "user" database entry.
 *
 * @property id Primary key ID
 * @property username Unique string identifier
 * @property passwordHash Encrypted password
 * @property createdAt Date this user was created
 * @property email
 * @property coinBalance How many coins this user has
 * @property role Privilege level of the user (user, moderator, admin)
 */
@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: UUID? = null,
    val username: String = "",
    @Column(name = "password_hash") val passwordHash: String = "",
    @Column(name = "created_at") val createdAt: LocalDateTime = LocalDateTime.now(),
    val email: String? = null,
    @Column(name = "coin_balance") val coinBalance: BigDecimal = BigDecimal.ZERO,
    val role: String = UserRole.USER,
)
