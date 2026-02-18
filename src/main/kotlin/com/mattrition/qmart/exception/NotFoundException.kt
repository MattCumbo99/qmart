package com.mattrition.qmart.exception

/**
 * Exception which is thrown when attempting to retrieve an unknown entity that should be
 * guaranteed.
 *
 * Status code: 404
 */
class NotFoundException(
    override val message: String,
) : RuntimeException(message)
