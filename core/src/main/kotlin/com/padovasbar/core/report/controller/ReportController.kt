package com.padovasbar.core.report.controller

import com.padovasbar.core.dto.PendentOrderResponseDTO
import com.padovasbar.core.model.OrderHistory
import com.padovasbar.core.model.PaymentType
import com.padovasbar.core.report.dto.OutcomeDTO
import com.padovasbar.core.report.dto.ValueSummarizedDTO
import com.padovasbar.core.report.dto.ValueSummarizedPerHourDTO
import com.padovasbar.core.report.repository.OutcomeRepository
import java.time.format.DateTimeFormatter
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/report")
class ReportController(private val outcomeRepository: OutcomeRepository) {

    @GetMapping("/outcome")
    fun outcome(@RequestParam since: Long): MutableList<OutcomeDTO> {
        val orders = outcomeRepository.outcome(since)
        val response = mutableListOf<OutcomeDTO>()

        orders
            .forEach {
                response.add(
                    OutcomeDTO(
                        it.orderHistoryId,
                        it.name,
                        it.statusChangedAt!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                        it.totalValue!!,
                        paymentTypeTranslate(it.paymentType!!)
                    )
                )
            }

        return response
    }

    @GetMapping("/value-summarized")
    fun valueSummarized(@RequestParam since: Long): ValueSummarizedDTO {
        val orders = outcomeRepository.outcome(since)
        val response = ValueSummarizedDTO(0.0, 0.0, 0.0)

        orders.forEach {
            when (it.paymentType) {
                PaymentType.CASH -> response.cash += it.totalValue?:0.0
                PaymentType.DEBIT_CARD -> response.debitCard += it.totalValue?:0.0
                PaymentType.CREDIT_CARD -> response.creditCard += it.totalValue?:0.0
            }
        }

        println(response)
        return response
    }

    @GetMapping("/value-summarized-per-hour")
    fun valueSummarizedPerHour(): ValueSummarizedPerHourDTO {
        val orders = outcomeRepository.outcome(0)

        //fazer select no banco por hora

        return response
    }


        fun paymentTypeTranslate(paymentType: PaymentType) =
        when (paymentType) {
            PaymentType.CASH -> "Dinheiro"
            PaymentType.DEBIT_CARD -> "Cartão de débito"
            PaymentType.CREDIT_CARD -> "Cartão de crédito"
        }


}