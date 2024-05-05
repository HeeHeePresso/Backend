package org.heeheepresso.gateway.common.response

import org.heeheepresso.gateway.common.response.StatusCode.SUCCESS

data class GatewayResponse<T>(
        val resultCode: Int,
        val resultMessage: String,
        val data: T?
) {
    constructor(data: T?) : this(SUCCESS.code, SUCCESS.message, data)

    constructor(statusCode: StatusCode) : this(statusCode.code, statusCode.message, null)
}