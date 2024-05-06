package org.heeheepresso.gateway.processor.post

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.menu.detail.MenuDetailService
import org.heeheepresso.gateway.search.SearchResponse
import org.heeheepresso.gateway.search.SearchResponse.Companion.MENU_DETAIL_IDS
import org.springframework.stereotype.Service

@Service
class MenuDetailSearchProcessor(
    private val menuDetailService: MenuDetailService
): PostProcessor {

    override suspend fun process(response: SearchResponse) {
        return coroutineScope {
            val menuDetails = async {
                menuDetailService.getMenuDetails(response.getTotalMenuIds()) }.await()
            response.extra[MENU_DETAIL_IDS] = menuDetails
        }
    }
}