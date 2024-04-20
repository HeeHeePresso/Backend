package org.heeheepresso.paymentapi.kakao.api

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payment")
class KakaoPayController(
) {

    @GetMapping("/approve/{orderId}")
    fun approve(@PathVariable orderId: String, @RequestParam pg_token: String) {
        println(orderId)
        println(pg_token)
    }
}