package com.mattrition.qmart.auth

data class PasswordCheckRequest(
    val username: String,
    val password: String
)
