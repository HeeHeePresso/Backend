package org.heeheepresso.gateway.recommendation.client

import org.apache.http.HttpStatus

data class ErrorResponse(
        val httpStatus: HttpStatus,
        val code: Int,
        val message: String
)