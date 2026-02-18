package com.mattrition.qmart.auth

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: CustomUserDetailsService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        // If no authorization header or not bearer, just continue the chain
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring("Bearer ".length)
        val username = jwtService.extractUsername(jwt)

        // Only authenticate if we have a username and no auth set yet
        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val customUserDetails = userDetailsService.loadUserByUsername(username)

            // Check if the incoming JWT is valid first!
            if (jwtService.validToken(jwt, customUserDetails)) {
                val authToken =
                    UsernamePasswordAuthenticationToken(
                        customUserDetails,
                        null,
                        customUserDetails.authorities,
                    )

                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }
}
