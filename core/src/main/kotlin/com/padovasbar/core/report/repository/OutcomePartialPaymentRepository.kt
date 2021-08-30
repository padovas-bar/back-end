package com.padovasbar.core.report.repository

import com.padovasbar.core.model.OrderHistory
import com.padovasbar.core.model.PartialPaymentHistory
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface OutcomePartialPaymentRepository : CrudRepository<PartialPaymentHistory, Long> {
    @Query(
        value = "select * from partial_payment_history\n" +
                "where paid_at > trunc(sysdate - interval '3' hour) - :since\n" +
                "order by paid_at desc",
        nativeQuery = true
    )
    fun outcomePartialPayments(@Param("since") since: Long) : MutableIterable<PartialPaymentHistory>


}