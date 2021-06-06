package com.padovasbar.core.report.repository

import com.padovasbar.core.model.Order
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OutcomeRepository : CrudRepository<Order, Long> {
    @Query(
        value = "select * from orders_history\n" +
                "where status = 'CLOSED'\n" +
                "and status_changed_at > trunc(sysdate) - :since;",
        nativeQuery = true)
    fun outcome(@Param("since") since: Long): MutableIterable<Order>

}