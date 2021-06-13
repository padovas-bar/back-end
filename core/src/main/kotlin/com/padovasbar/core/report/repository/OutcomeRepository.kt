package com.padovasbar.core.report.repository

import com.padovasbar.core.model.OrderHistory
import com.padovasbar.core.model.PartialPaymentHistory
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OutcomeRepository : CrudRepository<OrderHistory, Long> {
    @Query(
        value = "select a.id_order_history, a.name, a.status, a.status_changed_at, a.total_value - nvl(b.value, 0) as total_value, a.pendent_owner, a.payment_type\n" +
                "from orders_history a, partial_payment_history b\n" +
                "where a.status = 'CLOSED'\n" +
                "and a.id_order_history = b.id_order_history(+)\n" +
                "and a.status_changed_at > trunc(sysdate - interval '3' hour) - :since\n" +
                "order by a.status_changed_at",
        nativeQuery = true)
    fun outcome(@Param("since") since: Long): MutableIterable<OrderHistory>

}