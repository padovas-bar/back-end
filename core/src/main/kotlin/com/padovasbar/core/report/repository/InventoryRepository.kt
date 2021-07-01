package com.padovasbar.core.report.repository

import com.padovasbar.core.model.OrderHistory
import com.padovasbar.core.model.OrderItemsHistory
import java.time.LocalDateTime
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository : CrudRepository<OrderItemsHistory, Long> {
    @Query(
        value = "select * from order_items_history \n" +
                "where item_ordered_at > trunc(sysdate - interval '3' hour) - :since",
        nativeQuery = true)
    fun inventory(@Param("since") since: Long): MutableIterable<OrderItemsHistory>

    @Query(
        value = "select * from(\n" +
                "select name, sum(quantity) total\n" +
                "from order_items_history\n" +
                "where trunc(item_ordered_at) >= trunc(sysdate - interval '3' hour) - :since\n" +
                "group by name" +
                "order by 2)\n" +
                "where ROWNUM <= 10",
        nativeQuery = true)
    fun rank(@Param("since") since: Long): MutableIterable<Rank>

    interface Rank{
        fun getName(): String
        fun getTotal(): Long
    }
}