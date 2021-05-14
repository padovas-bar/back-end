package com.padovasbar.core.controller

import com.padovasbar.core.dto.CategoryResponseDTO
import com.padovasbar.core.dto.ProductResponseDTO
import com.padovasbar.core.model.Category
import com.padovasbar.core.model.PartialPayment
import com.padovasbar.core.model.PartialPaymentHistory
import com.padovasbar.core.repository.CategoryRepository
import com.padovasbar.core.repository.PartialPaymentHistoryRepository
import com.padovasbar.core.repository.PartialPaymentRepository
import com.padovasbar.core.repository.ProductRepository
import java.time.LocalDateTime
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/partial-payment")
class PartialPaymentController(
    private val partialPaymentRepository: PartialPaymentRepository,
    private val partialPaymentHistoryRepository: PartialPaymentHistoryRepository
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = partialPaymentRepository.findAllByOrderIdOrderByOrderIdAsc(id)

    @GetMapping("/history/{id}")
    fun getHistory(@PathVariable id: Long) = partialPaymentHistoryRepository.findAllByOrderHistoryIdOrderByOrderHistoryIdAsc(id)

    @PostMapping
    fun create(@RequestBody partialPayment: PartialPayment): PartialPayment {
        partialPayment.paidAt = LocalDateTime.now()
        return partialPaymentRepository.save(partialPayment)
    }

    @PostMapping("/history")
    fun createHistory(@RequestBody partialPaymentHistory: PartialPaymentHistory): PartialPaymentHistory {
        partialPaymentHistory.paidAt = LocalDateTime.now()
        return partialPaymentHistoryRepository.save(partialPaymentHistory)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = partialPaymentRepository.deleteById(id)

}