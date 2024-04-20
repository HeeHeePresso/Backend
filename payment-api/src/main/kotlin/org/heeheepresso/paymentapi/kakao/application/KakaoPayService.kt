package org.heeheepresso.paymentapi.kakao.application

import org.heeheepresso.paymentapi.common.ApiException
import org.heeheepresso.paymentapi.common.ApiStatus
import org.heeheepresso.paymentapi.kakao.config.KakaoApiProperties
import org.heeheepresso.paymentapi.kakao.dto.request.KakaoPayApproveRequest
import org.heeheepresso.paymentapi.kakao.dto.request.KakaoPayReadyRequest
import org.heeheepresso.paymentapi.kakao.dto.response.KakaoPayApproveResponse
import org.heeheepresso.paymentapi.kakao.dto.response.KakaoPayReadyResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class KakaoPayService(
    private val kakaoPayWebClient: WebClient,
    private val kakaoApiProperties: KakaoApiProperties,
) {

    /**
     * 카카오페이 결제 준비 요청
     * 카카오페이 결제를 시작하기 위해 결제정보를 카카오페이 서버에 전달하고 결제 고유번호(TID)와 URL을 응답받는 단계입니다.
     *
     * @param paymentId     카카오 페이 결제 고유 ID
     * @param orderId       주문 고유 ID
     * @param userId        유저 고유 ID
     * @param totalAmount   상품 총액
     * @return
     */
    fun ready(paymentId: Long, orderId: Long, userId: Long, totalAmount: Int): KakaoPayReadyResponse =
        kakaoPayWebClient.post()
            .uri("/ready")
            .bodyValue(KakaoPayReadyRequest.of(paymentId, orderId, userId, totalAmount, kakaoApiProperties))
            .retrieve()
            .bodyToMono(KakaoPayReadyResponse::class.java)
            .block() ?: throw ApiException(ApiStatus.KAKAO_PAY_CLIENT_ERROR)

    /**
     * 카카오페이 결제 승인 요청
     * 사용자가 결제 수단을 선택하고 비밀번호를 입력해 결제 인증을 완료한 뒤, 최종적으로 결제 완료 처리를 하는 단계입니다.
     *
     * @param tid       카카오 페이 결제 고유번호
     * @param orderId   주문 고유 ID
     * @param userId    유저 고유 ID
     * @param pgToken   결제승인 요청 인증 토큰
     * @return
     */
    fun approve(tid: String, orderId: String, userId: String, pgToken: String): KakaoPayApproveResponse =
        kakaoPayWebClient.post()
            .uri("/approve")
            .bodyValue(KakaoPayApproveRequest.of(tid, orderId, userId, pgToken, kakaoApiProperties))
            .retrieve()
            .bodyToMono(KakaoPayApproveResponse::class.java)
            .block() ?: throw ApiException(ApiStatus.KAKAO_PAY_CLIENT_ERROR)

    fun order(tid: String):  {

    }
}