package com.padovasbar.core.repository

import com.padovasbar.core.model.OrderItems
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository : CrudRepository<OrderItems, Long>{
    fun findAllByOrderIdOrderByOrderItemIdAsc(id: Long): MutableIterable<OrderItems>
}