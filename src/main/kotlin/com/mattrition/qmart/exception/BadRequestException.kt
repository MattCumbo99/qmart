package com.mattrition.qmart.exception

/**
 * Exception which is thrown when data contains an invalid value, like quantity being below 1.
 *
 * Status code: 400
 */
class BadRequestException(
    override val message: String,
) : RuntimeException(message)
