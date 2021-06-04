package com.padovasbar.core.dto

import com.padovasbar.core.model.PaymentType

data class Resume (var total: Double?,
                   var trustedClientName: String?,
                    var paymentType: PaymentType?
)