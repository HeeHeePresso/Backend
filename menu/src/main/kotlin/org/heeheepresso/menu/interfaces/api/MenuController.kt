package org.heeheepresso.menu.interfaces.api

import org.heeheepresso.menu.domain.menu.model.Menu
import org.heeheepresso.menu.domain.menu.service.MenuService
import org.heeheepresso.menu.interfaces.dto.ModifyMenuRequest
import org.heeheepresso.menu.interfaces.dto.ModifyStatusRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MenuController (
    val menuService: MenuService
        ){

    @GetMapping("/menus")
    fun getAllMenus(): Iterable<Menu> {
        return menuService.findAll()
    }

    @GetMapping("/menus/{menuId}")
    fun getMenuById(@PathVariable("menuId") menuId: Long): Menu{
        return menuService.findById(menuId)
    }

    @PostMapping("/menus")
    fun createMenu(@RequestBody menu: Menu){
        menuService.save(menu)
    }

    @PutMapping("/menus/{menuId}/status")
    fun changeMenuStatus(@PathVariable("menuId") menuId: Long, @RequestBody modifyStatusRequest: ModifyStatusRequest){
        menuService.modifyStatus(menuId, modifyStatusRequest)
    }
}