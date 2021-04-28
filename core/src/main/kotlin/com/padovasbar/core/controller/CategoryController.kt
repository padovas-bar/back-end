package com.padovasbar.core.controller

import com.padovasbar.core.model.Category
import com.padovasbar.core.repository.CategoryRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryController(private val categoryRepository: CategoryRepository) {

    @PostMapping
    fun create(@RequestBody category: Category){
        categoryRepository.save(category)
    }
}