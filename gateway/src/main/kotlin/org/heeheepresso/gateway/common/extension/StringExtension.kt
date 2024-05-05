package org.heeheepresso.gateway.common.extension

import org.heeheepresso.gateway.menu.category.MenuCategory
import org.heeheepresso.gateway.search.request.SearchRequestHandler
import org.heeheepresso.gateway.search.request.SearchRequestHandler.HOME

class StringExtension {

}

internal fun String.getMenuCategory(): MenuCategory {
    return MenuCategory.valueOf(this)
}

internal fun String.convertRecommendedUrl(): String {
    return when(this) {
        HOME.name -> "/home/recommend"
        else -> ""
    }
}