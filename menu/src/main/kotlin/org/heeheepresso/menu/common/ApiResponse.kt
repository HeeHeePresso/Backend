package org.heeheepresso.menu.common

data class ApiResponse<T>(
        var resultCode: Int,
        var resultMessage: String,
        var data: T,
) {
    companion object {
        fun of(status: ApiStatus, data: Any): ApiResponse<Any> {
            return ApiResponse(status.resultCode, status.resultMessage, data)
        }
    }
}