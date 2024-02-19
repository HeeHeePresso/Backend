package org.heeheepresso.gateway.menu

import org.heeheepresso.gateway.menu.dto.MenuDetailResponse
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MenuService {

    @Cacheable("menuDetail")
    suspend fun getMenuDetail(menuId : String): MenuDetailResponse {
        return MenuDetailResponse(menuId)
    }
}