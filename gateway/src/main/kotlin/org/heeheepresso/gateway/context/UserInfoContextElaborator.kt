package org.heeheepresso.gateway.context

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.heeheepresso.gateway.search.SearchContext
import org.heeheepresso.gateway.user.UserService
import org.springframework.stereotype.Service

@Service
class UserInfoContextElaborator(
    private val userService: UserService,
): ContextElaborator {
    override suspend fun elaborate(searchContext: SearchContext) {
        return coroutineScope {
            val userInfo = searchContext.getUserInfo()
            val storeId = async { userService.getStore(userInfo.userId) }
            searchContext.decorateUserInfo(userInfo.userId, storeId.await())
        }
    }
}