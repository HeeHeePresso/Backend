package org.heeheepresso.orderapi.order.domain.model

import java.lang.RuntimeException

enum class OrderStatus(
    val desc: String
) {
    REQUESTED("요청"),
    CONFIRMED("확인"),
    WAITING("픽업대기"),
    COMPLETED("픽업완료"),
    CANCELLED("취소"),;

    fun checkCanChangeable(nextStatus: OrderStatus) {
        if (!canChangeable(nextStatus)) {
            //TODO('공통 Exception으로 변경')
            throw RuntimeException()
        }
    }

    private fun canChangeable(nextStatus: OrderStatus): Boolean {
        if (this == nextStatus) {
            //TODO('같은 status로 온 변경 요청은 어떻게 처리할지 고민')
            return true
        }
        return when(this) {
            REQUESTED -> nextStatus == CONFIRMED || nextStatus == CANCELLED
            CONFIRMED -> nextStatus == WAITING
            WAITING -> nextStatus == CONFIRMED
            COMPLETED, CANCELLED -> false
        }
    }
}