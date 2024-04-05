package org.heeheepresso.menu.interfaces.api

import org.heeheepresso.menu.domain.menu.model.Menu
import org.heeheepresso.menu.domain.menu.service.MenuService
import org.heeheepresso.menu.interfaces.dto.ModifyStatusRequest
import org.springframework.web.bind.annotation.*

@RestController
class MenuController(
        val menuService: MenuService
) {

    @GetMapping("/menus")
    fun getMenus(@RequestParam(required = false) id: List<Long>?): Iterable<Menu> {
        return menuService.findMenus(id)
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