package com.padovasbar.core.report.repository

import com.padovasbar.core.model.OrderHistory
import java.time.LocalDateTime
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

    @Query(
        value = "select to_char(day, 'dd/MM/yyyy') day, sum(total_value) as value from\n" +
                "(select trunc(a.status_changed_at) day, sum(a.total_value - nvl(b.value, 0)) as total_value\n" +
                "from orders_history a, partial_payment_history b\n" +
                "where a.status = 'CLOSED'\n" +
                "and a.id_order_history = b.id_order_history(+)\n" +
                "and trunc(a.status_changed_at) >= trunc(sysdate - interval '3' hour) - 16\n" +
                "group by trunc(a.status_changed_at)\n" +
                "union all\n" +
                "select trunc(paid_at) day, sum(value) total_value from partial_payment_history\n" +
                "where trunc(paid_at) >= trunc(sysdate - interval '3' hour) - :since\n" +
                "group by trunc(paid_at))\n" +
                "group by day\n" +
                "order by 1",
        nativeQuery = true)
    fun outcomeFromDay(@Param("since") since: Long): MutableIterable<Sum>

    interface Sum{
        fun getDay(): String
        fun getValue(): Double
    }

}