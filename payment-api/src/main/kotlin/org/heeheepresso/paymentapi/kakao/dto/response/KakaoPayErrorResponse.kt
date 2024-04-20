package org.heeheepresso.paymentapi.kakao.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoPayErrorResponse(
    @JsonProperty("error_code") val errorCode: Int,
    @JsonProperty("error_message") val errorMessage: String,
    val extras: Extras,
) {
    data class Extras(
        @JsonProperty("method_result_code") val methodResultCode: String,
        @JsonProperty("method_result_message") val methodResultMessage: String
    )
}

