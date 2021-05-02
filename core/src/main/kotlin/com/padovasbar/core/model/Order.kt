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
@Table(name = "orders")
data class Order(

    @Id
    @GeneratedValue(generator = "seq_order")
    @Column(name = "id_order", nullable = false)
    val orderId: Long,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    val status: Status,

    @Column(name = "status_changed_at", nullable = false)
    val statusChangedAt: LocalDateTime,

    )
