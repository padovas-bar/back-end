package com.padovasbar.core.repository

import com.padovasbar.core.model.OrderItems
import com.padovasbar.core.model.PartialPayment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PartialPaymentRepository : CrudRepository<PartialPayment, Long>{
    fun findAllByOrderIdOrderByOrderIdAsc(id: Long): MutableIterable<PartialPayment>
    fun deleteAllByOrderId(id: Long)
}