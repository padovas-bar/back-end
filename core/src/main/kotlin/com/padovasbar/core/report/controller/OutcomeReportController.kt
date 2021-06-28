package com.padovasbar.core.report.controller

import com.padovasbar.core.model.PaymentType
import com.padovasbar.core.report.dto.OutcomeDTO
import com.padovasbar.core.report.dto.ValueSummarizedDTO
import com.padovasbar.core.report.repository.OutcomePartialPaymentRepository
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
class OutcomeReportController(
    private val outcomeRepository: OutcomeRepository,
    private val outcomePartialPaymentRepository: OutcomePartialPaymentRepository
) {

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

        val partialPayments = outcomePartialPaymentRepository.outcomePartialPayments(since)

        partialPayments
            .forEach {
                response.add(
                    OutcomeDTO(
                        it.orderHistoryId,
                        it.description,
                        it.paidAt!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                        it.value,
                        paymentTypeTranslate(it.paymentType!!)
                    )
                )
            }

        return response
    }

    @GetMapping("/value-summarized")
    fun valueSummarized(@RequestParam since: Long): ValueSummarizedDTO {
        val orders = outcomeRepository.outcome(since)
        val response = ValueSummarizedDTO(0.0, 0.0, 0.0, 0.0)

        orders.forEach {
            when (it.paymentType) {
                PaymentType.CASH -> response.cash += it.totalValue ?: 0.0
                PaymentType.DEBIT_CARD -> response.debitCard += it.totalValue ?: 0.0
                PaymentType.CREDIT_CARD -> response.creditCard += it.totalValue ?: 0.0
            }
        }

        val partialPayments = outcomePartialPaymentRepository.outcomePartialPayments(since)

        partialPayments.forEach {
            when (it.paymentType) {
                PaymentType.CASH -> response.cash += it.value
                PaymentType.DEBIT_CARD -> response.debitCard += it.value
                PaymentType.CREDIT_CARD -> response.creditCard += it.value
            }
        }

        response.total = response.cash + response.creditCard + response.debitCard
        return response
    }

    @GetMapping("/total-per-day")
    fun totalPerDay(@RequestParam since: Long): MutableIterable<OutcomeRepository.Sum> {

        return outcomeRepository.outcomeFromDay(since)

    }


    fun paymentTypeTranslate(paymentType: PaymentType) =
        when (paymentType) {
            PaymentType.CASH -> "Dinheiro"
            PaymentType.DEBIT_CARD -> "Cartão de débito"
            PaymentType.CREDIT_CARD -> "Cartão de crédito"
        }


}