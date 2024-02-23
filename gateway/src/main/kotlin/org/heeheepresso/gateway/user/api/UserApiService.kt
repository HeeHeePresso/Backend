package org.heeheepresso.gateway.user.api

import org.heeheepresso.gateway.user.UserRole
import org.springframework.stereotype.Service

@Service
class UserApiService {
    fun getUserRole(userId: String): UserRole {
        return UserRole.CUSTOMER
    }
}