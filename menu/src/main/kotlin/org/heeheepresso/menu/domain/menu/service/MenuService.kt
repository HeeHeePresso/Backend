package org.heeheepresso.menu.domain.menu.service

import org.heeheepresso.menu.domain.menu.model.Menu
import org.heeheepresso.menu.domain.menu.repository.MenuRepository
import org.heeheepresso.menu.interfaces.dto.ModifyStatusRequest
import org.springframework.stereotype.Service

@Service
class MenuService(
        val menuRepository: MenuRepository
) {

    fun save(input: Menu): Menu {
        return menuRepository.save(input)
    }

    fun findById(id: Long): Menu {
        return menuRepository.findById(id).orElseThrow()
    }

    fun findMenus(id: List<Long>?): Iterable<Menu> {
        return if (id == null)
            menuRepository.findAll()
        else {
            menuRepository.findAllById(id)
        }
    }

    fun modifyStatus(id: Long, request: ModifyStatusRequest): Menu {
        val menu: Menu = menuRepository.findById(id).orElseThrow()
        menu.modifyStatus(request.status)
        return menu
    }
}