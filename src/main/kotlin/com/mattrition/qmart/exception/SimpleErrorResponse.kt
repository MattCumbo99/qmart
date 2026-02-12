package com.mattrition.qmart.exception

import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.web.ErrorResponse

class SimpleErrorResponse(
    private val status: HttpStatusCode,
    private val errorMessage: String,
) : ErrorResponse {
    override fun getStatusCode() = status

    override fun getBody(): ProblemDetail {
        val pd = ProblemDetail.forStatus(status)
        pd.detail = errorMessage

        return pd
    }
}
