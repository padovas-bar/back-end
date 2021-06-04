package com.padovasbar.core.controller

import com.padovasbar.core.dto.ProductCategoryResponseDTO
import com.padovasbar.core.dto.ProductResponseDTO
import com.padovasbar.core.model.Product
import com.padovasbar.core.repository.CategoryRepository
import com.padovasbar.core.repository.ProductRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/product")
class ProductController(private val productRepository: ProductRepository,
                        private val categoryRepository: CategoryRepository) {

    @PostMapping
    fun create(@RequestBody product: Product): ProductCategoryResponseDTO {
        val savedProduct = productRepository.save(product)
        val category = categoryRepository.findById(product.categoryId!!).get()

        return ProductCategoryResponseDTO(
            savedProduct.productId!!,
            savedProduct.productId,
            category.name,
            savedProduct.name,
            savedProduct.price
        )
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = productRepository.findById(id)

    @GetMapping
    fun getAll(): MutableList<ProductResponseDTO> {
        val products = productRepository.findAll()

        val response = mutableListOf<ProductResponseDTO>()
        for(product in products){
            val category = categoryRepository.findById(product.categoryId!!).get()
            response.add(ProductResponseDTO(product.productId!!, product.categoryId!!, category.name, product.name, product.price))
        }

        return response
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody product: Product): Product {
        val savedProduct = productRepository.findById(id).get()
        savedProduct.name = product.name
        savedProduct.price = product.price
        savedProduct.categoryId = product.categoryId
        return productRepository.save(savedProduct)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = productRepository.deleteById(id)

}