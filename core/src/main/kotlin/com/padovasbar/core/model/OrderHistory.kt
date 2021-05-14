package com.padovasbar.core.model

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "orders_history")
data class OrderHistory(

    @Id
    @Column(name = "id_order_history", nullable = false)
    val orderHistoryId: Long?,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    var status: Status?,

    @Column(name = "status_changed_at", nullable = false)
    var statusChangedAt: LocalDateTime?,

    @Column(name = "total_value", nullable = false)
    var totalValue: Double?,

    @Column(name = "pendent_owner")
    var pendentOwner: String?

    )
