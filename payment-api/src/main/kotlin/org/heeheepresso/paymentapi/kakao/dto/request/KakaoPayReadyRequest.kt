package org.heeheepresso.paymentapi.kakao.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.heeheepresso.paymentapi.kakao.config.KakaoApiProperties
import org.heeheepresso.paymentapi.payment.domain.model.enum.PaymentType
import java.time.LocalDateTime

data class KakaoPayReadyRequest(
    @JsonProperty("cid") val cid: String,
    @JsonProperty("partner_order_id") val partnerOrderId: String,
    @JsonProperty("partner_user_id") val partnerUserId: String,
    @JsonProperty("item_name") val itemName: String,
    @JsonProperty("quantity") val quantity: Int,
    @JsonProperty("total_amount") val totalAmount: Int,
    @JsonProperty("tax_free_amount") val taxFreeAmount: Int,
    @JsonProperty("approval_url") val approvalUrl: String,
    @JsonProperty("cancel_url") val cancelUrl: String,
    @JsonProperty("fail_url") val failUrl: String
) {
    companion object {
        fun of(
            paymentId: Long,
            orderId: Long,
            userId: Long,
            totalAmount: Int,
            apiProperties: KakaoApiProperties
        ): KakaoPayReadyRequest =
            KakaoPayReadyRequest(
                apiProperties.cid,
                orderId.toString(),
                userId.toString(),
                "${PaymentType.KAKAO.desc} - $orderId - $userId - ${LocalDateTime.now()}",
                1,
                totalAmount,
                0,
                apiProperties.url.approve + paymentId,
                apiProperties.url.cancel + paymentId,
                apiProperties.url.fail + paymentId
            )
    }
}