package org.heeheepresso.menu.common

data class ApiResponse<T>(
        var resultCode: Int,
        var resultMessage: String,
        var data: T,
)