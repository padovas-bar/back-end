package com.padovasbar.core.repository

import com.padovasbar.core.model.Category
import com.padovasbar.core.model.Order
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Long>{
    @Query(
        value = "SELECT * FROM orders where status = 'OPEN' order by id_order",
        nativeQuery = true)
    override fun findAll(): MutableIterable<Order>

    @Query(
        value = "SELECT * FROM orders where status = 'PENDENT' order by id_order",
        nativeQuery = true)
    fun findAllPendents(): MutableIterable<Order>
}