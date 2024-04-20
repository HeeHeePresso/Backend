package org.heeheepresso.paymentapi.payment.domain.model

import jakarta.persistence.*
import org.heeheepresso.paymentapi.common.BaseEntity
import org.heeheepresso.paymentapi.payment.domain.model.enum.PaymentStatus
import org.heeheepresso.paymentapi.payment.domain.model.enum.PaymentType
import java.math.BigDecimal

@Entity
@Table(name = "payment")
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val orderId: Long,
    val userId: Long,
    val amount: BigDecimal,
    val name: String,
    @Enumerated(value = EnumType.STRING)
    var status: PaymentStatus,
    @Enumerated(value = EnumType.STRING)
    val type: PaymentType,
    var failReason: String,
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment", cascade = [CascadeType.ALL], orphanRemoval = true)
    var kaKaoPayment: KaKaoPayment,
) : BaseEntity() {
}