package com.padovasbar.core.dto

import com.padovasbar.core.model.Status
import java.time.LocalDateTime

data class OrderResponseDTO (
    val orderId: Long,
    val name: String,
    val status: Status,
    val statusChangedAt: LocalDateTime,
    val orderItems: List<OrderItemsResponseDTO>?

    )

data class OrderItemsResponseDTO (
    val orderItemId: Long?,
    val orderId: Long?,
    val productId: Long?,
    val productName: String?,
    val quantity: String?,
    val itemOrderedAt: LocalDateTime?
    )
