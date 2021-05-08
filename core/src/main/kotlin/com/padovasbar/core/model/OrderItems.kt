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
data class OrderItems(

    @Id
    @GeneratedValue(generator = "seq_order_items")
    @Column(name = "id_order_item", nullable = false)
    val orderItemId: Long?,

    @Column(name = "id_order", nullable = false)
    val orderId: Long?,

    @Column(name = "id_product")
    var productId: Long?,

    @Column(name = "quantity")
    var quantity: String?,

    @Column(name = "item_ordered_at", nullable = false)
    var itemOrderedAt: LocalDateTime?

    )
