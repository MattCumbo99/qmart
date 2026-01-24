package com.mattrition.qmart.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsernameIgnoreCase(username: String): User?

    fun findById(id: UUID): User?
}