package com.padovasbar.core.repository

import com.padovasbar.core.model.PartialPayment
import com.padovasbar.core.model.PartialPaymentHistory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PartialPaymentHistoryRepository : CrudRepository<PartialPaymentHistory, Long>{
}