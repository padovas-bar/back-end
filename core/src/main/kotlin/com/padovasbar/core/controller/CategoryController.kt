package com.padovasbar.core.controller

import com.padovasbar.core.dto.CategoryResponseDTO
import com.padovasbar.core.dto.ProductResponseDTO
import com.padovasbar.core.model.Category
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
@RequestMapping("/category")
class CategoryController(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository
) {

    @PostMapping
    fun create(@RequestBody category: Category) = categoryRepository.save(category)

    @GetMapping
    fun getAll() : MutableList<CategoryResponseDTO>{
        val response =  mutableListOf<CategoryResponseDTO>()

        for(category in categoryRepository.findAll()){
            val categoryResponse = CategoryResponseDTO(category.categoryId, category.name, mutableListOf<ProductResponseDTO>())

            for(product in productRepository.findAllByCategoryId(categoryResponse.categoryId!!)){
                val productResponse = ProductResponseDTO(product.productId!!, product.categoryId!!, product.name, product.price)
                categoryResponse.products?.add(productResponse)
            }

            response.add(categoryResponse)
        }

        return response
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = categoryRepository.deleteById(id)

}