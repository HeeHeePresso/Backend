package org.heeheepresso.gateway.order.dto

import org.heeheepresso.gateway.user.UserRole

data class OrderCommandApiRequest(
        val orderId: Long,
        val userId: String,
        val userRole: UserRole,
)
