package org.heeheepresso.gateway.common.exception

import org.heeheepresso.gateway.common.response.StatusCode
import org.springframework.http.HttpStatus

data class GatewayException(
        val httpStatus: HttpStatus,
        val statusCode: StatusCode
) : RuntimeException(statusCode.message) {
    constructor(statusCode: StatusCode) : this(statusCode.status, statusCode)
}