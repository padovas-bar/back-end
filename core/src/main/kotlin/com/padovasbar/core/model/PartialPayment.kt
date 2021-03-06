package com.padovasbar.core.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class PartialPayment(

    @Id
    @GeneratedValue(generator = "seq_partial_payment")
    @Column(name = "id_partial_payment", nullable = false)
    val partialPaymentId: Long?,

    @Column(name = "id_order", nullable = false)
    val orderId: Long?,

    @Column(name = "description")
    var description: String?,

    @Column(name = "value", nullable = false)
    val value: Double,

    @Column(name = "paid_at", nullable = false)
    var paidAt: LocalDateTime?,

    @Column(name = "payment_type")
    @Enumerated(value = EnumType.STRING)
    var paymentType: PaymentType?

    )
