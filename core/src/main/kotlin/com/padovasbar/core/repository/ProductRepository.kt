package com.padovasbar.core.repository

import com.padovasbar.core.model.Category
import com.padovasbar.core.model.OrderItems
import com.padovasbar.core.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<Product, Long>{
    fun findAllByCategoryId(id: Long): MutableIterable<Product>
}