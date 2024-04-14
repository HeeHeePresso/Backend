package org.heeheepresso.gateway.common.response

enum class MenuApiStatus(
        val resultCode: Int,
        val resultMessage: String,
) {
    SUCCESS(0, "성공"),
}