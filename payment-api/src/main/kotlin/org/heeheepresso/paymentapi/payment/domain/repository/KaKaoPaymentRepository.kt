package org.heeheepresso.paymentapi.payment.domain.repository

import org.heeheepresso.paymentapi.payment.domain.model.KaKaoPayment
import org.heeheepresso.paymentapi.payment.domain.model.Payment
import org.springframework.data.jpa.repository.JpaRepository

interface KaKaoPaymentRepository : JpaRepository<KaKaoPayment, Long> {
}