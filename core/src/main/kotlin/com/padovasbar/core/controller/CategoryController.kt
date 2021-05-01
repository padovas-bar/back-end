package com.padovasbar.core.controller

import com.padovasbar.core.model.Category
import com.padovasbar.core.repository.CategoryRepository
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
class CategoryController(private val categoryRepository: CategoryRepository) {

    @PostMapping
    fun create(@RequestBody category: Category) = categoryRepository.save(category)

    @GetMapping
    fun getAll() = categoryRepository.findAll()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = categoryRepository.deleteById(id)

}