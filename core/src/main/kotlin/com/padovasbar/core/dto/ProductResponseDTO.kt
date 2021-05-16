package com.padovasbar.core.dto

data class CategoryResponseDTO(
    val categoryId: Long?,
    val name: String,
    val products: MutableList<ProductResponseDTO>?
)

data class ProductResponseDTO(
    val productId: Long,
    val categoryId: Long,
    val categoryName: String?,
    val name: String,
    val price: Double
)