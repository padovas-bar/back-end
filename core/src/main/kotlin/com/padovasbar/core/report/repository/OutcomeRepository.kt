package com.padovasbar.core.report.repository

import com.padovasbar.core.model.OrderHistory
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OutcomeRepository : CrudRepository<OrderHistory, Long> {
    @Query(
        value = "select * from orders_history\n" +
                "where status = 'CLOSED'\n" +
                "and status_changed_at > trunc(sysdate) - :since\n" +
                "order by status_changed_at",
        nativeQuery = true)
    fun outcome(@Param("since") since: Long): MutableIterable<OrderHistory>

}