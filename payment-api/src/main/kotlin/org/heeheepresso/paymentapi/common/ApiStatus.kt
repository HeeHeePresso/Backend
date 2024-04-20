package org.heeheepresso.paymentapi.common

enum class ApiStatus(
    override val code: Int,
    override val message: String,
    override val isShort: Boolean,
    override val isSendErrorMessage: Boolean
) : ApiStatusResponsible {
    SUCCESS(0, "성공", false, false),
    FAIL(1, "실패", false, false),
    KAKAO_PAY_CLIENT_ERROR(-1001, "카카오 페이 API 요청 실패", false, false),
    KAKAO_PAY_SERVER_ERROR(-1002, "카카오 페이 API 서버 에러", false, false),
}