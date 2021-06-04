package com.padovasbar.core.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class TrustedClient(

    @Id
    @GeneratedValue(generator = "seq_trusted_client")
    @Column(name = "id_trusted_client", nullable = false)
    val trustedClientId: Long?,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description")
    var description: String?,

    )
