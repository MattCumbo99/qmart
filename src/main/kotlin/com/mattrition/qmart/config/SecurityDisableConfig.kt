package com.mattrition.qmart.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

/*
 * This class is here to prevent the use of auth headers. Remove this if planning to
 * publish this project to a production branch.
 */
@Configuration
class SecurityDisableConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }.authorizeHttpRequests { it.anyRequest().permitAll() }
        return http.build()
    }
}
