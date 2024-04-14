package org.heeheepresso.gateway.common.response

data class MenuApiResponse<T>(
        var resultCode: Int,
        var resultMessage: String,
        var data: T,
) {
    companion object {
        fun of(status: MenuApiStatus, data: Any): MenuApiResponse<Any> {
            return MenuApiResponse(status.resultCode, status.resultMessage, data)
        }
    }
}