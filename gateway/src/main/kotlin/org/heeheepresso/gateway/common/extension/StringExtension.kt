package org.heeheepresso.gateway.common.extension

import org.heeheepresso.gateway.menu.category.MenuCategory

class StringExtension {

}

internal fun String.getMenuCategory(): MenuCategory {
    return MenuCategory.valueOf(this)
}