package org.heeheepresso.paymentapi.kakao.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoPayApproveResponse(
    @JsonProperty("aid") val aid: String,
    @JsonProperty("tid") val tid: String,
    @JsonProperty("cid") val cid: String,
    @JsonProperty("sid") val sid: Any?,
    @JsonProperty("partner_order_id") val partnerOrderId: String,
    @JsonProperty("partner_user_id") val partnerUserId: String,
    @JsonProperty("item_name") val itemName: String,
    @JsonProperty("item_code") val itemCode: Any?,
    @JsonProperty("payload") val payload: Any?,
    @JsonProperty("quantity") val quantity: Int,
    @JsonProperty("amount") val amount: Amount,
    @JsonProperty("payment_method_type") val paymentMethodType: String,
    @JsonProperty("card_info") val cardInfo: Any?,
    @JsonProperty("sequential_payment_methods") val sequentialPaymentMethods: Any?,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("approved_at") val approvedAt: String
) {
    data class Amount(
        @JsonProperty("total") val total: Int,
        @JsonProperty("tax_free") val taxFree: Int,
        @JsonProperty("vat") val vat: Int,
        @JsonProperty("point") val point: Int,
        @JsonProperty("discount") val discount: Int,
        @JsonProperty("green_deposit") val greenDeposit: Int
    )
}
