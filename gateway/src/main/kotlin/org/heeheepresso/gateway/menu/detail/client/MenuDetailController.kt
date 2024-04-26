package org.heeheepresso.gateway.menu.detail.client

import mu.KotlinLogging
import org.heeheepresso.gateway.common.response.MenuApiResponse
import org.heeheepresso.gateway.menu.domain.MenuInfo
import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientRequestException
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono

@RestController
class MenuDetailController(
        private val menuWebClient: WebClient,
) {
    private val logger = KotlinLogging.logger {}
    private val menuInfoType = object : ParameterizedTypeReference<MenuApiResponse<List<MenuInfo>>>() {}

    suspend fun callMenuInfo(menuIds: List<Long>): Mono<MenuApiResponse<List<MenuInfo>>> {
        return menuWebClient.get()
                .uri {
                    it.path("/menus")
                            .queryParam("id", menuIds)
                            .build()
                }
                .retrieve()
                .bodyToMono(menuInfoType)
                .onErrorResume(WebClientRequestException::class.java) {
                    logger.error { "[Menu] Connection Error: ${it.message}" }
                    Mono.empty()
                }
                .onErrorResume(WebClientResponseException::class.java) {
                    logger.error { "[Menu] Exception Message: (${it.statusCode}) - ${it.getResponseBodyAs(menuInfoType)?.resultMessage}" }
                    Mono.empty()
                }
    }
}