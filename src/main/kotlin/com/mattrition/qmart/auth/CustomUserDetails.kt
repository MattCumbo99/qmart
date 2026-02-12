package com.mattrition.qmart.auth

import com.mattrition.qmart.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

/**
 * Serves as a way to translate a [User] entity to a Spring-regulated [UserDetails] object. The `id`
 * property in this class can be accessed by calling `authentication.principal.id` within a
 * "PreAuthorize" annotation.
 */
class CustomUserDetails(
    private val user: User,
) : UserDetails {
    val id: UUID
        get() = user.id!!

    override fun getAuthorities(): Collection<GrantedAuthority> = listOf(SimpleGrantedAuthority("ROLE_${user.role.uppercase()}"))

    override fun getPassword(): String = user.passwordHash

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
