package com.padovasbar.core.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Category(

    @Id
    @GeneratedValue(generator = "seq_category")
    @Column(name = "id_category", nullable = false)
    val categoryId: Long?,

    @Column(name = "name", nullable = false)
    val name: String

    )
