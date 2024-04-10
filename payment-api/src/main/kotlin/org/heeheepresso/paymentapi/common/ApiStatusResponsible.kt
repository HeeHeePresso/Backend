package org.heeheepresso.paymentapi.common

interface ApiStatusResponsible {
    val code: Int
    val message: String
    val isShort: Boolean
    val isSendErrorMessage: Boolean
}
