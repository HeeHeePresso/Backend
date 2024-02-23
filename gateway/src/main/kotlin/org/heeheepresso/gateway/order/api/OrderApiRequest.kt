package org.heeheepresso.gateway.order.api

import org.heeheepresso.gateway.user.UserRole

data class OrderApiRequest(
    val userId: String,
    val userRole: UserRole,
)
