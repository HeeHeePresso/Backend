package org.heeheepresso.paymentapi.kakao.config

import org.heeheepresso.paymentapi.common.ApiStatusResponsible

data class KakaoApiStatus(
    override val code: Int,
    override val message: String,
    override val isShort: Boolean = false,
    override val isSendErrorMessage: Boolean = false,
) : ApiStatusResponsible
