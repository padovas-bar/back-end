package com.padovasbar.core.report.dto

import java.time.LocalDateTime

data class OutcomeDTO(
    val orderHistoryId: Long?,
    var name: String?,
    var statusChangedAt: String,
    var originalStatusChangedAt: LocalDateTime,
    var totalValue: Double,
    var paymentType: String
)
