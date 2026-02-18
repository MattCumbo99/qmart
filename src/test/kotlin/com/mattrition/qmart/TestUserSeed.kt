package com.mattrition.qmart

/** Object mapping seed for grabbing information from the users.json file. */
data class TestUserSeed(
    val username: String,
    val password: String,
    val email: String?,
    val role: String,
)
