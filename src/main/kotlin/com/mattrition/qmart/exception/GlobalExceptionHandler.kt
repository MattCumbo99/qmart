package com.mattrition.qmart.exception

import org.springframework.http.HttpStatus
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ErrorResponse = SimpleErrorResponse(HttpStatus.NOT_FOUND, ex.message)

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbidden(ex: ForbiddenException): ErrorResponse = SimpleErrorResponse(HttpStatus.FORBIDDEN, ex.message)

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(ex: BadRequestException): ErrorResponse = SimpleErrorResponse(HttpStatus.BAD_REQUEST, ex.message)

    @ExceptionHandler(ConflictException::class)
    fun handleConflict(ex: ConflictException): ErrorResponse = SimpleErrorResponse(HttpStatus.CONFLICT, ex.message)
}
