package com.mattrition.qmart.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsernameIgnoreCase(username: String): User?
}