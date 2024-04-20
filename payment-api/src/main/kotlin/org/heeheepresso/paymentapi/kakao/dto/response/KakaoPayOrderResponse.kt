package org.heeheepresso.paymentapi.kakao.dto.response

import java.time.LocalDateTime


data class KakaoPayOrderResponse(
    val tid: String,
    val cid: String,
    val status: String,
    val partnerOrderId: String,
    val partnerUserId: String,
    val paymentMethodType: String,
    val itemName: String,
    val itemCode: String?,
    val quantity: Int,
    val createdAt: LocalDateTime,
    val approvedAt: LocalDateTime,
    val canceledAt: LocalDateTime?,
    val amount: Amount,
    val canceledAmount: Amount,
    val cancelAvailableAmount: Amount,
    val selectedCardInfo: CardInfo?,
    val paymentActionDetails: List<PaymentActionDetail>
) {
    data class Amount(
        val total: Int,
        val taxFree: Int,
        val vat: Int,
        val point: Int,
        val discount: Int,
        val greenDeposit: Int
    )

    data class CardInfo(
        // Define properties for card information if necessary
    )


    data class PaymentActionDetail(
        val aid: String,
        val approvedAt: LocalDateTime,
        val pointAmount: Int,
        val discountAmount: Int,
        val greenDepositAmount: Int,
        val paymentActionType: String
    )
}





