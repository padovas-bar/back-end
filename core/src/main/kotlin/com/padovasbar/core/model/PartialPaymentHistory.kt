package com.padovasbar.core.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class PartialPaymentHistory(

    @Id
    @GeneratedValue(generator = "seq_partial_payment")
    @Column(name = "id_partial_payment_history", nullable = false)
    val partialPaymentHistoryId: Long?,

    @Column(name = "id_order_history", nullable = false)
    val orderHistoryId: Long?,

    @Column(name = "description")
    val description: String?,

    @Column(name = "value", nullable = false)
    val value: Double,

    @Column(name = "paid_at", nullable = false)
    var paidAt: LocalDateTime?

    )
