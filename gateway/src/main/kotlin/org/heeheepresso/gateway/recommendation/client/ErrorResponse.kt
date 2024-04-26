package org.heeheepresso.gateway.recommendation.client

data class ErrorResponse(
//        val httpStatus: HttpStatus,
        val code: Int,
        val message: String
)