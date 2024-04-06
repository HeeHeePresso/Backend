package org.heeheepresso.menu.interfaces.api

import org.heeheepresso.menu.common.ApiListResponse
import org.heeheepresso.menu.common.ApiStatus
import org.heeheepresso.menu.domain.menu.model.Menu
import org.heeheepresso.menu.domain.menu.service.MenuService
import org.heeheepresso.menu.interfaces.dto.GetMenusResponse
import org.heeheepresso.menu.interfaces.dto.ModifyStatusRequest
import org.springframework.web.bind.annotation.*

@RestController
class MenuController(
        val menuService: MenuService
) {

    @GetMapping("/menus")
    fun getMenus(@RequestParam(required = false) id: List<Long>?): ApiListResponse<*> {
        val menus: List<GetMenusResponse> = menuService.findMenus(id).map { menu -> GetMenusResponse.from(menu) }
        return ApiListResponse.of(ApiStatus.SUCCESS, menus)
    }

    @GetMapping("/menus/{menuId}")
    fun getMenuById(@PathVariable("menuId") menuId: Long): Menu {
        return menuService.findById(menuId)
    }

    @PostMapping("/menus")
    fun createMenu(@RequestBody menu: Menu) {
        menuService.save(menu)
    }

    @PutMapping("/menus/{menuId}/status")
    fun changeMenuStatus(@PathVariable("menuId") menuId: Long, @RequestBody modifyStatusRequest: ModifyStatusRequest) {
        menuService.modifyStatus(menuId, modifyStatusRequest)
    }
}