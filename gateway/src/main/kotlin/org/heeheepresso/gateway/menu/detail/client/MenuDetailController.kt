package org.heeheepresso.gateway.menu.detail.client

import org.heeheepresso.gateway.common.response.MenuApiResponse
import org.heeheepresso.gateway.menu.domain.MenuInfo
import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
class MenuDetailController(
        private val menuWebClient: WebClient,
) {

    suspend fun callMenuInfo(menuIds: List<Long>): Mono<MenuApiResponse<List<MenuInfo>>> {
        return menuWebClient.get()
                .uri {
                    it.path("/menus")
                            .queryParam("id", menuIds)
                            .build()
                }
                .retrieve()
                .bodyToMono(object : ParameterizedTypeReference<MenuApiResponse<List<MenuInfo>>>() {})
    }
}