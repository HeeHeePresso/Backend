package org.heeheepresso.gateway.menu.domain

data class MenuBase(
        val menuId: Long,
        val name: String,
        val price: Long,
        val thumbnailImageUrl: String
)
