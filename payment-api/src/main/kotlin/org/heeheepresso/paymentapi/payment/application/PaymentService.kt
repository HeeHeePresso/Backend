package org.heeheepresso.paymentapi.payment.application

import org.heeheepresso.paymentapi.payment.domain.repository.PaymentRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

//    fun createPayment() : Payment = paymentRepository.save()
}