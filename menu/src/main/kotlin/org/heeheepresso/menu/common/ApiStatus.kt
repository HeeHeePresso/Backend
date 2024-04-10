package org.heeheepresso.menu.common

enum class ApiStatus(
        val resultCode: Int,
        val resultMessage: String,
) {
    SUCCESS(0, "성공"),
}