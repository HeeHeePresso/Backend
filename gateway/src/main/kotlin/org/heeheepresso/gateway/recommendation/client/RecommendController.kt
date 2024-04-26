package org.heeheepresso.gateway.recommendation.client

import mu.KotlinLogging
import org.heeheepresso.gateway.recommendation.RecommendedRequest
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientRequestException
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono

@RestController
class RecommendController(
        private val recommendWebClient: WebClient
) {
    private val logger = KotlinLogging.logger {}

    suspend fun callHomeRecommendMenus(request: RecommendedRequest): Mono<HomeRecommendResponse> {
        return recommendWebClient
                .post()
                .uri("/home/recommend")
                .body(Mono.just(request), RecommendedRequest::class.java)
                .retrieve()
                .bodyToMono(HomeRecommendResponse::class.java)
                .onErrorResume(WebClientRequestException::class.java) {
                    logger.error { "[RCMM] Connection Error: ${it.message}" }
                    Mono.empty()
                }
                .onErrorResume(WebClientResponseException::class.java) {
                    logger.error { "[RCMM] Exception Message: (${it.statusCode}) - ${it.message}" }
                    Mono.empty()
                }
    }
}