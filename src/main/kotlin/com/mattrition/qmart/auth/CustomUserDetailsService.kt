package com.mattrition.qmart.auth

import com.mattrition.qmart.user.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): CustomUserDetails {
        val user =
            userRepository.findByUsernameIgnoreCase(username)
                ?: throw UsernameNotFoundException("User $username not found")

        return CustomUserDetails(user)
    }
}
