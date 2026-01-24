package com.mattrition.qmart.user

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class UserDto(
    val id: UUID,
    val username: String,
    val email: String?,
    val createdAt: LocalDateTime,
    val coinBalance: BigDecimal = BigDecimal.ZERO,
    val role: String = UserRole.USER
)
