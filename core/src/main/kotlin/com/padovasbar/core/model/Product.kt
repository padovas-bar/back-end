package com.padovasbar.core.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Product(

    @Id
    @GeneratedValue(generator = "seq_product")
    @Column(name = "id_product", nullable = false)
    val productId: Long?,

    @Column(name = "id_category", nullable = false)
    val categoryId: Long?,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "price", nullable = false)
    val price: Double

    )
