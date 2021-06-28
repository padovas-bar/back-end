package com.padovasbar.core.report.dto

data class InventoryDTO(
    val orderItemHistoryId: Long?,
    var name: String?,
    var itemOrderedAt: String,
    var quantity: Long,
    var price: Double
)
