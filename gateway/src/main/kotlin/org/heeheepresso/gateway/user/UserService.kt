package org.heeheepresso.gateway.user

import org.heeheepresso.gateway.user.api.UserApiService
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class UserService(
    private val userApi: UserApiService
) {

    private val userRoleCache = ConcurrentHashMap<String, UserRole>()
    fun getUserRole(userId: String): UserRole? {
        return when {
            userRoleCache.containsKey(userId) -> userRoleCache[userId]
            else -> userApi.getUserRole(userId)
        }
    }
}