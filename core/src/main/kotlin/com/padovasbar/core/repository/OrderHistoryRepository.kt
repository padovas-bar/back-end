package com.padovasbar.core.repository

import com.padovasbar.core.model.OrderHistory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderHistoryRepository : CrudRepository<OrderHistory, Long>