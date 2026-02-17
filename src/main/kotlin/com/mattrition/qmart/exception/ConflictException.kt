package com.mattrition.qmart.exception

/**
 * Exception for attempts to save an entity which already exists.
 *
 * Status code: 409
 */
class ConflictException(
    override val message: String,
) : RuntimeException(message)
