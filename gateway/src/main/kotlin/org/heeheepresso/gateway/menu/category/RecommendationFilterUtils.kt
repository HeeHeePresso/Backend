package org.heeheepresso.gateway.menu.category

class RecommendationFilterUtils {

    companion object {
        fun addCategoryFilter(menuCategory: MenuCategory): String {
            return "category={${menuCategory.name}"
        }
    }
}
