package com.padovasbar.core.repository

import com.padovasbar.core.model.Order
import com.padovasbar.core.model.OrderHistory
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderHistoryRepository : CrudRepository<OrderHistory, Long>{
    @Query(
        value = "SELECT * FROM orders_history where status = 'PENDENT' order by id_order_history",
        nativeQuery = true)
    fun findAllPendents(): MutableIterable<OrderHistory>
}