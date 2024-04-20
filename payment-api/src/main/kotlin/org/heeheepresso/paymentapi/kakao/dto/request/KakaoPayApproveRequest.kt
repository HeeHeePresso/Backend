package org.heeheepresso.paymentapi.kakao.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.heeheepresso.paymentapi.kakao.config.KakaoApiProperties

data class KakaoPayApproveRequest(
    @JsonProperty("tid") val tid: String,
    @JsonProperty("cid") val cid: String,
    @JsonProperty("partner_order_id") val partnerOrderId: String,
    @JsonProperty("partner_user_id") val partnerUserId: String,
    @JsonProperty("pg_token") val pgToken: String
) {
    companion object {
        fun of(
            tid: String,
            orderId: String,
            userId: String,
            pgToken: String,
            apiProperties: KakaoApiProperties
        ): KakaoPayApproveRequest =
            KakaoPayApproveRequest(
                tid,
                apiProperties.cid,
                orderId,
                userId,
                pgToken
            )
    }
}