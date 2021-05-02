package com.padovasbar.core.repository

import com.padovasbar.core.model.Category
import com.padovasbar.core.model.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : CrudRepository<Order, Long>