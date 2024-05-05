package org.heeheepresso.gateway.common.response

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class StatusCode(
        val code: Int,
        val message: String,
        val status: HttpStatus
) {
    SUCCESS(0, "요청 성공", OK),
    SERVER_ERROR(100, "예상치 못한 서버 에러", INTERNAL_SERVER_ERROR),
    NOT_VALID_INPUT(999, "잘못된 입력", BAD_REQUEST)
}