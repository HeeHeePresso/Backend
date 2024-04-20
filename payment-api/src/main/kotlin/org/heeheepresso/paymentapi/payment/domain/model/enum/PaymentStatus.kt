package org.heeheepresso.paymentapi.payment.domain.model.enum

enum class PaymentStatus(
    val desc: String
) {
    REQUESTED("요청"),
    APPROVED("승인"),
    FAILED("실패"),
    CANCELED("취소"),
}