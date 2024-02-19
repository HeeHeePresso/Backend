package org.heeheepresso.gateway.menu

import kotlinx.coroutines.reactor.mono
import org.heeheepresso.gateway.menu.dto.MenuDetailResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/menu")
class MenuController(
    private val menuService: MenuService
) {
    @GetMapping
    fun getMenuDetail(@RequestParam("menuId") menuId : String): Mono<MenuDetailResponse> {
        return mono {
            menuService.getMenuDetail(menuId)
        }
    }
}