package com.padovasbar.core.dto

import com.padovasbar.core.model.Status
import java.time.LocalDateTime

data class OrderResponseDTO (
    val orderId: Long,
    val name: String?,
    val status: Status,
    val statusChangedAt: LocalDateTime,
    var orderItems: List<OrderItemsResponseDTO>?

    )

data class OrderItemsResponseDTO (
    val orderItemId: Long?,
    val orderId: Long?,
    val productId: Long?,
    var productName: String?,
    var productPrice: Double?,
    val quantity: Long?,
    val itemOrderedAt: LocalDateTime?
    )
