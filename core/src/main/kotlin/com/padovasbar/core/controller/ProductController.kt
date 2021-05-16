package com.padovasbar.core.controller

import com.padovasbar.core.dto.ProductResponseDTO
import com.padovasbar.core.model.Category
import com.padovasbar.core.model.PartialPaymentHistory
import com.padovasbar.core.model.Product
import com.padovasbar.core.repository.CategoryRepository
import com.padovasbar.core.repository.ProductRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/product")
class ProductController(private val productRepository: ProductRepository,
                        private val categoryRepository: CategoryRepository) {

    @PostMapping
    fun create(@RequestBody product: Product) = productRepository.save(product)

    @GetMapping
    fun getAll(): MutableList<ProductResponseDTO> {
        val products = productRepository.findAll()

        val response = mutableListOf<ProductResponseDTO>()
        for(product in products){
            val category = categoryRepository.findById(product.categoryId!!).get()
            response.add(ProductResponseDTO(product.productId!!, product.categoryId, category.name, product.name, product.price))
        }

        return response
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = productRepository.deleteById(id)

}