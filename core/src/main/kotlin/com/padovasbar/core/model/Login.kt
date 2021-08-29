package com.padovasbar.core.model

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
data class Login(

    @Id
    @Column(name = "id_login", nullable = false)
    val loginId: Long?,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "last_login_at", nullable = false)
    var lastLoginAt: LocalDateTime,


    )
