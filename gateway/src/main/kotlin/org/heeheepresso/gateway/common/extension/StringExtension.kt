package org.heeheepresso.gateway.common.extension

import org.heeheepresso.gateway.menu.category.MenuCategory
import org.heeheepresso.gateway.search.request.SearchRequestHandler.MENU_CATEGORY
import org.heeheepresso.gateway.search.request.SearchRequestHandler.HOME

class StringExtension {

}

internal fun String.getMenuCategory(): MenuCategory {
    return MenuCategory.valueOf(this)
}

internal fun String.convertRecommendedUrl(): String {
    return when(this) {
        HOME.name -> "/home/recommend"
//        MENU_CATEGORY.name -> "/menu/category/recommend"
        MENU_CATEGORY.name -> "/home/recommend" // 테스트 때문에 임시로
        else -> ""
    }
}