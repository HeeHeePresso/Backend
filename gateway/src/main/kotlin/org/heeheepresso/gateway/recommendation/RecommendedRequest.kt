package org.heeheepresso.gateway.recommendation

data class RecommendedRequest(
    val handler: String,
    val where: String?,
    val userId: Long,
    val storeId: Long,
    val pageSize: Int,
    val offset: Int,
)
