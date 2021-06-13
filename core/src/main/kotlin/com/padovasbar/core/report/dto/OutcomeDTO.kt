package com.padovasbar.core.report.dto

data class OutcomeDTO(
    val orderHistoryId: Long?,
    var name: String?,
    var statusChangedAt: String,
    var totalValue: Double,
    var paymentType: String
)
