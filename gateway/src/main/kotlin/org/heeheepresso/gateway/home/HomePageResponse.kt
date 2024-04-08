package org.heeheepresso.gateway.home

data class HomePageResponse(
        val eventUrls: List<String>,
        val menuInfos: List<MenuResult>
)
