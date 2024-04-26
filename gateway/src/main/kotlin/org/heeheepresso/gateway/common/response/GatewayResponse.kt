package org.heeheepresso.gateway.common.response


private val success = StatusCode.SUCCESS

data class GatewayResponse<T>(
        val resultCode: Int,
        val resultMessage: String,
        val data: T?
) {
    constructor(data: T?) : this(success.code, success.message, data)

    constructor(statusCode: StatusCode) : this(statusCode.code, statusCode.message, null)
}