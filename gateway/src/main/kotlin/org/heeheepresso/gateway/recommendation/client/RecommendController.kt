package org.heeheepresso.gateway.recommendation.client

import org.heeheepresso.gateway.recommendation.RecommendedRequest
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class RecommendController(
        private val webClientBuilder: WebClient.Builder
) {

    companion object {
        private const val SERVICE_NAME = "recommend-service"
    }

    suspend fun callHomeRecommendMenus(request: RecommendedRequest): Mono<HomeRecommendResponse> {
        return client()
                .post()
                .uri("/home/recommend")
                .body(request, RecommendedRequest::class.java)
                .retrieve()
                .bodyToMono(HomeRecommendResponse::class.java)
    }

    private fun client(): WebClient {
        return this.webClientBuilder
                .baseUrl("http://${SERVICE_NAME}")
                .build()
    }
}