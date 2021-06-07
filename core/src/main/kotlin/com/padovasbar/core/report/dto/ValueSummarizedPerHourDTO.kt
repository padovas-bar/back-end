package com.padovasbar.core.report.dto

data class ValueSummarizedPerHourDTO(
    var cash: Map<Long, Double>,
    var debitCard: Map<Long, Double>,
    var creditCard: Map<Long, Double>
)
