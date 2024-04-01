package org.heeheepresso.orderapi.common

data class ApiException(
    val apiStatusResponsible: ApiStatusResponsible
) : RuntimeException(apiStatusResponsible.message)