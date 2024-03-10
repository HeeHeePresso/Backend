package org.heeheepresso.gateway.menu.domain

import org.heeheepresso.gateway.menu.category.MenuCategory

data class Menu(
        val menuCategory: MenuCategory,
        val imagePath: String,
        val title: String,
        val subTitle: String,
        val price: Long,
)
