package org.heeheepresso.gateway.menu.category

class CategoryFilterUtils {

    companion object {
        fun addCategoryFilter(menuCategory: MenuCategory): String {
            return "category={${menuCategory.name}"
        }
    }
}
