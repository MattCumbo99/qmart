package com.mattrition.qmart.user

data class RegistrationInfo(
    val username: String,
    val rawPassword: String,
    val email: String? = null,
)
