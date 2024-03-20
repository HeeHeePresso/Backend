package org.heeheepresso.orderapi.common

data class ApiException(
    private val apiStatusResponsible: ApiStatusResponsible
) : RuntimeException(apiStatusResponsible.message)