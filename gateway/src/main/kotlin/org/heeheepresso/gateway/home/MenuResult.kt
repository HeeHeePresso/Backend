package org.heeheepresso.gateway.home

import org.heeheepresso.gateway.menu.domain.MenuBase

data class MenuResult(
        val title: String,
        val menus: List<MenuBase>
)
