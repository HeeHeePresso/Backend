package org.heeheepresso.paymentapi.payment.domain.model

import jakarta.persistence.*
import org.heeheepresso.paymentapi.common.BaseEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payment_kakao")
class KaKaoPayment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    val payment: Payment,
    val amount: BigDecimal,
    var canceledAmount: BigDecimal = BigDecimal.ZERO,
    val tid: String,
    var aid: String?,
    var sid: String?,
    val createdAt: LocalDateTime,
    var approvedAt: LocalDateTime?,
    var canceledAt: LocalDateTime?,
    var paymentMethodType: String?,
    val tmsResult: Boolean,
    var errorCode: String?,
    var errorMessage: String?
) : BaseEntity() {
}