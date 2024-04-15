package org.heeheepresso.gateway.menu.detail

import kotlinx.coroutines.reactor.awaitSingle
import org.heeheepresso.gateway.common.response.MenuApiStatus.*
import org.heeheepresso.gateway.menu.detail.client.MenuDetailController
import org.heeheepresso.gateway.menu.domain.MenuInfo
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MenuDetailService(
        private val menuDetailController: MenuDetailController
) {

    @Cacheable("menuDetail")
    suspend fun getMenuDetails(menuIds: List<Long>?): List<MenuInfo> {
        if (menuIds != null) {
            val callMenuInfo = menuDetailController.callMenuInfo(menuIds)
            val response = callMenuInfo.awaitSingle()

            if (response.resultCode == SUCCESS.resultCode) {
                return response.data
            }
        }
        return listOf()
    }
}