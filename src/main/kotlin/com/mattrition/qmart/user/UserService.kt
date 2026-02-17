package com.mattrition.qmart.user

import com.mattrition.qmart.exception.ConflictException
import com.mattrition.qmart.exception.NotFoundException
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
        if (repo.existsByUsernameIgnoreCase(registerInfo.username)) {
            throw ConflictException("User already exists.")
        }

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

    fun getUserById(id: UUID): UserDto = repo.findById(id).orElseThrow { NotFoundException("User with id $id not found.") }.toDto()

    fun getAllUsers(): List<UserDto> = repo.findAll().map { it.toDto() }

    fun getUserByUsername(username: String): UserDto =
        repo.findByUsernameIgnoreCase(username)?.toDto()
            ?: throw NotFoundException("User $username does not exist.")

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
