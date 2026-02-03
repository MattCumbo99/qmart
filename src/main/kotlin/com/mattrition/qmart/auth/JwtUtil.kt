package com.mattrition.qmart.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.util.Date
import java.util.UUID

@Component
class JwtUtil {
    private val secret = "1d2i0jf0923thg91wd1uegh03hth13irh9fj31h12rh129"
    private val key = Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(
        username: String,
        id: UUID,
        role: String,
    ): String {
        val now = Date()
        val expiry = Date(now.time + 1000 * 60 * 60 * 24) // 24 hours

        return Jwts
            .builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiry)
            .claim("uid", id)
            .claim("role", role)
            .signWith(key)
            .compact()
    }
}
