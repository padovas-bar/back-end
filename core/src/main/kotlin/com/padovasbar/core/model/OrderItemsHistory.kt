package com.padovasbar.core.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table
data class OrderItemsHistory(

    @Id
    @Column(name = "id_order_item_history", nullable = false)
    val orderItemHistoryId: Long?,

    @Column(name = "id_order_history", nullable = false)
    val orderHistoryId: Long?,

    @Column(name = "name")
    var name: String?,

    @Column(name = "price")
    var price: Double?,

    @Column(name = "quantity")
    var quantity: Long?,

    @Column(name = "item_ordered_at", nullable = false)
    var itemOrderedAt: LocalDateTime?

    )
