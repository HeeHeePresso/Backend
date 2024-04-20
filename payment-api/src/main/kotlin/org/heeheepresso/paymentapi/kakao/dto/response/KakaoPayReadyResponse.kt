package org.heeheepresso.paymentapi.kakao.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoPayReadyResponse(
    @JsonProperty("tid") val tid: String,
    @JsonProperty("tms_result") val tmsResult: Boolean,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("next_redirect_pc_url") val nextRedirectPcUrl: String,
    @JsonProperty("next_redirect_mobile_url") val nextRedirectMobileUrl: String,
    @JsonProperty("next_redirect_app_url") val nextRedirectAppUrl: String,
    @JsonProperty("android_app_scheme") val androidAppScheme: String,
    @JsonProperty("ios_app_scheme") val iosAppScheme: String
)