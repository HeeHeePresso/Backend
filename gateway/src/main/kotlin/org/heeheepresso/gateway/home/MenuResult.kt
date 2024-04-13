package org.heeheepresso.gateway.home

import org.heeheepresso.gateway.menu.domain.MenuBase

data class MenuResult(
        val handler: String,
        val menus: List<MenuBase>
)
