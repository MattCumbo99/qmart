package com.mattrition.qmart.auth

import com.mattrition.qmart.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {
    fun login(username: String, password: String): LoginResponse {
        val user = userRepository.findByUsernameIgnoreCase(username)
            ?: throw RuntimeException("User not found.")

        if (!passwordEncoder.matches(password, user.passwordHash)) {
            throw RuntimeException("Invalid credentials.")
        }

        val token = jwtUtil.generateToken(user.username, user.id!!, user.role)

        return LoginResponse(
            token = token,
            username = username
        )
    }
}