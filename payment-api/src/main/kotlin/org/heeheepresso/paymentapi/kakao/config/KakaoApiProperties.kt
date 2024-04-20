package org.heeheepresso.paymentapi.kakao.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding


@ConfigurationProperties(prefix = "api.kakao.pay")
data class KakaoApiProperties @ConstructorBinding constructor(
    val cid: String,
    val authorization: String,
    val url: UrlProperties,
) {
    data class UrlProperties(
        val base: String,
        val approve: String,
        val cancel: String,
        val fail: String,
    )
}