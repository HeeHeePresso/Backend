package org.heeheepresso.orderapi.order.domain.model

enum class OrderStatus(
    val desc: String
) {
    REQUESTED("요청"),
    CONFIRMED("확인"),
    WAITING("확인"),
    COMPLETED("완료"),
    CANCELLED("취소"),
}