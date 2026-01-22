package com.mattrition.qmart.user

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repo: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun createUser(user: RegisterUser): User {
        val hashed = passwordEncoder.encode(user.rawPassword)!!

        return repo.save(User(username = user.username, passwordHash = hashed))
    }

    fun getAllUsers(): List<User> = repo.findAll()

    fun getUserByUsername(username: String): User? {
        return repo.findByUsernameIgnoreCase(username)
    }

    fun passwordMatches(username: String, rawPassword: String): Boolean {
        val user = repo.findByUsernameIgnoreCase(username) ?: return false

        return passwordEncoder.matches(rawPassword, user.passwordHash)
    }
}