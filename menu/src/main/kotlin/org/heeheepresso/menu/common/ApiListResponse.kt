package org.heeheepresso.menu.common

data class ApiListResponse<T>(
        var resultCode: Int,
        var resultMessage: String,
        var data: List<T>,
) {
    companion object {
        fun of(status: ApiStatus, data: List<*>): ApiListResponse<*> {
            return ApiListResponse(status.resultCode, status.resultMessage, data)
        }
    }
}