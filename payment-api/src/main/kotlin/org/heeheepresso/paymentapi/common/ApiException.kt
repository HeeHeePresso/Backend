package org.heeheepresso.paymentapi.common

data class ApiException(
    val apiStatusResponsible: ApiStatusResponsible
) : RuntimeException(apiStatusResponsible.message)