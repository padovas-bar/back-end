package com.padovasbar.core.repository

import com.padovasbar.core.model.OrderItems
import com.padovasbar.core.model.OrderItemsHistory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemHistoryRepository : CrudRepository<OrderItemsHistory, Long>{
}