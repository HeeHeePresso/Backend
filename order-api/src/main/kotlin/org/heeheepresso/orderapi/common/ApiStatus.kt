package org.heeheepresso.orderapi.common

enum class ApiStatus(
    override val code: Int,
    override val message: String,
    override val isShort: Boolean,
    override val isSendErrorMessage: Boolean
) : ApiStatusResponsible {
    SUCCESS(0, "성공", false, false),
    FAIL(1, "실패", false, false),
    NOT_EXISTS_ORDER_HISTORY(1001, "존재하지 않는 주문 내역입니다.", false, false),
}