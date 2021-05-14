package com.padovasbar.core.dto

import com.padovasbar.core.model.Status
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EnumType
import javax.persistence.Enumerated

data class PendentOrderResponseDTO (
    val orderHistoryId: Long?,
    var name: String,
    var status: Status?,
    var statusChangedAt: LocalDateTime?,
    var totalValue: Double?,
    var pendentOwner: String?,
    var partialPaidValue: Double?,
    var remainingValue: Double?

    )

