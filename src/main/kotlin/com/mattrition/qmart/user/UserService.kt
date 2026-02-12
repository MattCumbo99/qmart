package com.mattrition.qmart.user

import com.mattrition.qmart.user.dto.RegistrationInfo
import com.mattrition.qmart.user.dto.UserDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val repo: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun createUser(registerInfo: RegistrationInfo): UserDto {
        val userEntity =
            repo.save(
                User(
                    username = registerInfo.username,
                    passwordHash = passwordEncoder.encode(registerInfo.rawPassword)!!,
                    email = registerInfo.email,
                ),
            )

        return userEntity.toDto()
    }

    fun getUserById(id: UUID): UserDto? = repo.findById(id)?.get()?.toDto()

    fun getAllUsers(): List<UserDto> = repo.findAll().map { it.toDto() }

    fun getUserByUsername(username: String): UserDto? = repo.findByUsernameIgnoreCase(username)?.toDto()

    /** Converts a [User] object to a Data Transfer Object (no password). */
    fun User.toDto(): UserDto =
        UserDto(
            id = this.id!!,
            username = this.username,
            email = this.email,
            createdAt = this.createdAt,
            coinBalance = this.coinBalance,
            role = this.role,
        )
}
