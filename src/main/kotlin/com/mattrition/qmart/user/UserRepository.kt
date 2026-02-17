package com.mattrition.qmart.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, UUID> {
    fun findByUsernameIgnoreCase(username: String): User?

    fun existsByUsernameIgnoreCase(username: String): Boolean
}
