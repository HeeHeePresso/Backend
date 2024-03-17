package org.heeheepresso.menu.domain.menu.service

import org.heeheepresso.menu.domain.menu.model.Menu
import org.heeheepresso.menu.domain.menu.repository.MenuRepository
import org.heeheepresso.menu.interfaces.dto.ModifyMenuRequest
import org.heeheepresso.menu.interfaces.dto.ModifyStatusRequest
import org.springframework.stereotype.Service

@Service
class MenuService (
    val menuRepository: MenuRepository
        ){

    fun save(input: Menu): Menu{
        return menuRepository.save(input)
    }

    fun findById(id: Long): Menu {
        return menuRepository.findById(id).orElseThrow()
    }

    fun findAll(): Iterable<Menu> {
        return menuRepository.findAll()
    }

    fun modifyStatus(id: Long, request: ModifyStatusRequest): Menu{
        val menu: Menu = menuRepository.findById(id).orElseThrow()
        menu.modifyStatus(request.status)
        return menu
    }

}