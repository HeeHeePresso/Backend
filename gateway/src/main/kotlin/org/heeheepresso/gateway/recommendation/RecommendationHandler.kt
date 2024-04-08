package org.heeheepresso.gateway.recommendation

enum class RecommendationHandler {
    NEWLY_RELEASED,
    HIGHLY_RECOMMENDED,
    BREAD,
    SEASON_RECOMMENDED,
    MENU_CATEGORY; // 카테고리에 따른 메뉴 목록 조회에 대한 핸들러

    companion object {
        fun convertStringsToHandlers(strings: List<String>): List<RecommendationHandler> {
            return strings.map { valueOf(it) }
        }
    }
}