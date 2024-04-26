package org.heeheepresso.gateway.menu.detail

import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.heeheepresso.gateway.common.response.MenuApiResponse
import org.heeheepresso.gateway.common.response.MenuApiStatus.*
import org.heeheepresso.gateway.menu.detail.client.MenuDetailController
import org.heeheepresso.gateway.menu.domain.MenuInfo
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MenuDetailService(
        private val menuDetailController: MenuDetailController
) {

    @Cacheable("menuDetail", unless = "#result == null || #result.size() == 0")
    suspend fun getMenuDetails(menuIds: List<Long>?): List<MenuInfo> {
        if (menuIds != null) {
            val callMenuInfo = menuDetailController.callMenuInfo(menuIds)
            val response = callMenuInfo.awaitSingleOrNull() ?: return listOf()

            return if (response.resultCode == SUCCESS.resultCode) {
                response.data
            } else {
                listOf()
            }
        }
        return listOf()
    }
}