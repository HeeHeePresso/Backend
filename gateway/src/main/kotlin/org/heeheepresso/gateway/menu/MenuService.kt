package org.heeheepresso.gateway.menu

import org.heeheepresso.gateway.menu.dto.MenuDetailResponse
import org.springframework.stereotype.Service

@Service
class MenuService {
    fun getMenuDetail(menuId : String): MenuDetailResponse {
        return MenuDetailResponse(menuId)
    }
}